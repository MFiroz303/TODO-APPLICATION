package com.bridgeit.todo.service;

import java.util.List;
import com.bridgeit.todo.model.Label;
import com.bridgeit.todo.model.Note;
import com.bridgeit.todo.model.User;

public interface NoteService {

	int saveNotes(Note note);

	void deleteNoteById(int id);

	List<Note> findAllNote(User user);

	Note getNoteById(int id);

	void updateNote(Note note);
	
	public List<Note> getSharedNotes(int id);
/*	void collaborateUser(User cUser, Note cNote);
*/
	public void removeCollabeUser(Note oldNote, User user);

	Label createLabel(User user, Label label);

}
