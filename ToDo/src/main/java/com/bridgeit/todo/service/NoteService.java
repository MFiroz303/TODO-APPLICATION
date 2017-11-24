package com.bridgeit.todo.service;

import java.util.List;

import com.bridgeit.todo.model.Note;
import com.bridgeit.todo.model.User;

public interface NoteService {

	int saveNotes(Note note);

	void deleteNoteById(int id);

	List<Note> findAllNote(User user);

	void updateNote(int id, Note note);

	Note getNoteById(int id);

}
