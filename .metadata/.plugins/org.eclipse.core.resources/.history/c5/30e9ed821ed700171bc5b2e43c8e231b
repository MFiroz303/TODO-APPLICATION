package com.bridgeit.todo.dao;

import java.util.List;

import com.bridgeit.todo.model.Note;
import com.bridgeit.todo.model.User;

public interface NoteDao {
	
	public int saveNotes(Note note);

	public void deleteNoteById(int id);

	void updateNote(int id, Note note);

	List<Note> findAllNote(User user);

	public Note getNoteById(int id);
}
