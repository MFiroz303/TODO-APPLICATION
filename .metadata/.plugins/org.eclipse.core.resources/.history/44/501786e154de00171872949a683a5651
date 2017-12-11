package com.bridgeit.todo.service;

import java.util.List;

import com.bridgeit.todo.model.Label;
import com.bridgeit.todo.model.Note;
import com.bridgeit.todo.model.User;

public interface NoteService {

	public int saveNotes(Note note);

	public void deleteNoteById(int id);

	public List<Note> findAllNote(User user);

	public Note getNoteById(int id);

	void updateNote(Note note);
	
	public List<Note> getSharedNotes(int id);
	
/*	void collaborateUser(User cUser, Note cNote);
*/
	public void removeCollabeUser(Note oldNote, User user);

	public Label createLabel(User user, Label label);

	public boolean deleteLabel(Label label, User user);


}
