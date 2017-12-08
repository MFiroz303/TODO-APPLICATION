package com.bridgeit.todo.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.todo.Token.TokenGenerate;
import com.bridgeit.todo.Token.VerifyJwt;
import com.bridgeit.todo.model.ErrorMessage;
import com.bridgeit.todo.model.Label;
import com.bridgeit.todo.model.Note;
import com.bridgeit.todo.model.User;
import com.bridgeit.todo.service.NoteService;
import com.bridgeit.todo.service.UserService;

@RestController
public class NoteController {

	@Autowired
	NoteService noteService;

	@Autowired
	ErrorMessage errorMessage;

	@Autowired
	UserService userService;

	@Autowired
	TokenGenerate tokenGenerate;

	@RequestMapping(value = "/addNote", method = RequestMethod.POST)
	public ResponseEntity<ErrorMessage> saveNotes(@RequestHeader("Authorization") String Authorization,
			@RequestBody Note note, HttpSession session) {

		System.out.println("user toke" + Authorization);
		int id = VerifyJwt.verify(Authorization);
		System.out.println("id in note is " + id);
		if (id == 0) {
			errorMessage.setResponseMessage("Data Successfully updated ");
		}
		User user1 = userService.getUserById(id);
		Date date = new Date();
		note.setCreatedDate(date);
		note.setModifiedDate(date);

		note.setUser(user1);
		int userId = noteService.saveNotes(note);

		if (userId != 0) {
			errorMessage.setResponseMessage("Data Successfully inserted ");
			return ResponseEntity.status(HttpStatus.CREATED).body(errorMessage);

		}
		errorMessage.setResponseMessage("Note could not be added");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		// return ResponseEntity.ok(errorMessage);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<ErrorMessage> updateNote(@RequestHeader("Authorization") String Authorization,
			@RequestBody Note note) {

		User user = userService.getUserById(VerifyJwt.verify(Authorization));
		Note oldNote = noteService.getNoteById(note.getNoteId());
		if (user != null) {

			if (oldNote.getUser().getId() == user.getId()) {
				note.setUser(user);

				noteService.updateNote(note);
				errorMessage.setResponseMessage("Data Successfully updated ");
				return ResponseEntity.ok(errorMessage);
			}

		}
		errorMessage.setResponseMessage("User is not logged in");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ErrorMessage> deleteNoteById(@PathVariable("id") int id) {

		noteService.deleteNoteById(id);
		errorMessage.setResponseMessage("Successfully deleted");
		return ResponseEntity.ok(errorMessage);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/noteList", method = RequestMethod.GET)
	public ResponseEntity<List<Note>> findAllNote(@RequestHeader("Authorization") String Authorization,
			HttpSession session, HttpServletRequest request) {

		
		int id = VerifyJwt.verify(Authorization);
		if (id == 0) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		User user1 = userService.getUserById(id);
		if(user1!=null) {
		List<Note> notes = noteService.findAllNote(user1);
	    List<Note> collabdNotes=noteService.getSharedNotes(user1.getId());
	    notes.addAll(collabdNotes);
	    System.out.println("");
	    return new  ResponseEntity(notes, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	

	@RequestMapping(value = "/getOwner", method = RequestMethod.POST)
	public ResponseEntity<User> getOwner(@RequestHeader("Authorization") String Authorization, @RequestBody Note note,
			HttpServletRequest request) {

		int id = VerifyJwt.verify(Authorization);
		User user = userService.getUserById(id);
		if (user != null) {
			Note owNote = noteService.getNoteById(note.getNoteId());
			return ResponseEntity.ok(owNote.getUser());
		} else {
			return ResponseEntity.ok(null);
		}
	}

	@RequestMapping(value = "/sharedUserNotes", method = RequestMethod.POST)
	public ResponseEntity<List<User>> sharedNotesUser(@RequestHeader("Authorization") String Authorization,
			@RequestBody Note note, HttpServletRequest request) {

		int id = VerifyJwt.verify(Authorization);
		User user = userService.getUserById(id);

		if (user != null) {
			Note userNote = noteService.getNoteById(note.getNoteId());
			if (userNote == null) {
				return null;
			}
			List<User> owner = userNote.getSharedUser();
			return ResponseEntity.ok(owner);
		} else {
			return ResponseEntity.ok(null);
		}
	}

	@RequestMapping(value = "/collaborator", method = RequestMethod.POST)
	public ResponseEntity<ErrorMessage> collaborator(@RequestHeader("Authorization") String Authorization,
			@RequestBody Note note, HttpServletRequest request) {
		System.out.println("HUHUHUHUHUh");
		int uId = VerifyJwt.verify(Authorization);
		if (uId < 0) {
			errorMessage.setResponseMessage("No user logged in");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
		}
		Note oldNote = noteService.getNoteById(note.getNoteId());
		User sharedUser;
		System.out.println("before: " + oldNote);

		try {
			sharedUser = userService.getUserByEmail(request.getHeader("email"));
			if (sharedUser == null) {
				System.out.println("No User Email found");
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
			}

		} catch (Exception E) {
			System.out.println("No User Email found");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
		}

		oldNote.getSharedUser().add(sharedUser);
		noteService.updateNote(oldNote);
		System.out.println("after: " + oldNote);
		return ResponseEntity.status(HttpStatus.OK).body(errorMessage);
	}
	
	@RequestMapping(value="/removeCollaborator",method=RequestMethod.POST)
	public ResponseEntity<Void> removeCollaborator(@RequestBody Note note,HttpServletRequest request)
	{
		Note oldNote = noteService.getNoteById(note.getNoteId());
		User user=userService.getUserByEmail(request.getHeader("email"));
		
		if(user!=null)
		{
			oldNote.getSharedUser().remove(user);
			noteService.updateNote(oldNote);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}
	
	
	@RequestMapping(value="/createLabel",  method =  RequestMethod.POST)
	public ResponseEntity<User> createLabel( @RequestHeader("Authorization") String Authorization,@RequestBody Label label, HttpServletRequest request,
			HttpServletResponse response) {
	
		int uId = VerifyJwt.verify(Authorization);
		User user = userService.getUserById(uId);
		
		if(user!=null){
		noteService.createLabel(user, label);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
		errorMessage.setResponseMessage("User not Exist");
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/getLabel", method = RequestMethod.POST)
	public ResponseEntity<Object> getAllLabel(@RequestHeader("Authorization") String Authorization,HttpServletRequest request)
	{
		int uId = VerifyJwt.verify(Authorization);
		User user = userService.getUserById(uId);
		
		Set<Label> labels=null;
		if(user!=null)
		{
			 labels= user.getLabels();
		}
		else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not logged in");
		}
		return ResponseEntity.ok(labels);
	}
	
	@RequestMapping(value="/deleteLabel",method=RequestMethod.POST)
	public ResponseEntity<Object> detelelabel (@RequestHeader("Authorization") String Authorization, @RequestBody Label label,HttpServletRequest request)
	{
		int uId = VerifyJwt.verify(Authorization);
		User user = userService.getUserById(uId);
		
		if(user!=null){
			noteService.deleteLabel(label, user);
		}else{
			errorMessage.setResponseMessage("User Not exits");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		}
		return new  ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		
	}
}
