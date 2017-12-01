package com.bridgeit.todo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.todo.Token.TokenGenerate;
import com.bridgeit.todo.Token.VerifyJwt;
import com.bridgeit.todo.model.Collaborator;
import com.bridgeit.todo.model.ErrorMessage;
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
	public ResponseEntity<ErrorMessage> saveNotes(@RequestHeader("Authorization") String Authorization,@RequestBody Note note, HttpSession session) {

		
		 System.out.println("user toke"+Authorization);
		 int id = VerifyJwt.verify(Authorization);
		 System.out.println("id in note is "+id);
		 if(id==0){
			 errorMessage.setResponseMessage("Data Successfully updated ");
		     }
		User user1= userService.getUserById(id);
		Date date = new Date();
		note.setCreatedDate(date);
		note.setModifiedDate(date);
		
		note.setUser(user1);
		int userId=noteService.saveNotes(note);

		if(userId!=0){
		errorMessage.setResponseMessage("Data Successfully inserted ");
		return ResponseEntity.status(HttpStatus.CREATED).body(errorMessage);
		
		}
		errorMessage.setResponseMessage("Note could not be added");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    //return ResponseEntity.ok(errorMessage);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<ErrorMessage> updateNote(@RequestHeader("Authorization") String Authorization, @RequestBody Note note) {
		
		
		User user = userService.getUserById(VerifyJwt.verify(Authorization));
		Note oldNote = noteService.getNoteById(note.getId());
		if(user!=null){
		
			if (oldNote.getUser().getId() == user.getId()) {
				note.setUser(user);
			
		      noteService.updateNote(note.getId(),note);
		      System.out.println("updated"+note.getId());
		      errorMessage.setResponseMessage("Data Successfully updated ");
		      return ResponseEntity.ok(errorMessage);
			}
			
	   }errorMessage.setResponseMessage("User is not logged in");
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
	public ResponseEntity<List<Note>> findAllNote(@RequestHeader("Authorization") String Authorization,HttpSession session,  HttpServletRequest request) {
	
		 /* String userToken = null;
		    Enumeration headerNames = request.getHeaderNames();
		    
		 while (headerNames.hasMoreElements()) {
			 String key = headerNames.nextElement().toString();
			 if (key.equals("Authorization")) {
			 userToken = request.getHeader(key);
			}
		 }*/
			 int id = VerifyJwt.verify(Authorization);
			 if(id==0){
				 return new ResponseEntity(HttpStatus.NOT_FOUND);
			 }
			 User user1= userService.getUserById(id);
		//User user = (User) session.getAttribute("user");
		List<Note> notes = noteService.findAllNote(user1);
		//List<Note> notes = noteService.getAllNotes(user);
		 return new ResponseEntity(notes,HttpStatus.OK);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String exceptionHandler(Exception e) {
		return "Exception";

	}
	
	@RequestMapping(value="/collaborate", method=RequestMethod.GET)
	public ResponseEntity<User>collaborateNotes(@RequestHeader("Authorization") String Authorization,
			                                     @RequestBody Note cNote,HttpServletRequest request,HttpServletResponse response ){

		     Collaborator collaborator = new Collaborator();
		     User loggedUser = userService.getUserById(VerifyJwt.verify(Authorization));
		     
		    if(cNote==null){
		    	return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		    }
		    
		    if(request.getHeader("userEmail")==null){
		    	return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		    }
		    
		   
		    if(userService.userExists(loggedUser)){
		    	  String email = request.getHeader("userEmail");
				  User cUser = userService.getUserByEmail(email);
		    }
	}}
		
	/*	if (cNote == null) {
			logger.info("Note Empty");
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		if (request.getHeader("userEmail") == null) {
			
		}
		
		String email = request.getHeader("userEmail");
		User cUser = new User();
		Integer userId = (Integer) request.getAttribute("userId");
		cUser = userService.getUserByEmail(email, cUser);
		if (!userService.userExists(cUser)) {
			logger.info("Invalid Email in collaborator");
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}

		logger.info("collaborating note with id: " + cNote.getNoteId());
		Integer cUserId = cUser.getId();
		// check if logged in user is editing note.
		// or collaborated users' editing note //
		User loggedUser = new User();
		loggedUser = userService.getUserById(userId, loggedUser);
		if (loggedUser.getId().compareTo(cNote.getUser().getId()) == 0 || cNote.getCollabUsers().contains(loggedUser)) {
			cUser = userService.getUserById(cUserId, cUser);
			try {
				noteService.collaborateUser(cUser, cNote);
			} catch (Exception E) {
				logger.info("User is already collaborated");
				return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
			}

		} else {
			logger.info("Note Owner Authorization failed");
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<User>(cUser, HttpStatus.OK);
	}	
		
		
		
}*/