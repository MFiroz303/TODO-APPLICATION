package com.bridgeit.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.todo.dao.NoteDao;
import com.bridgeit.todo.model.Label;
import com.bridgeit.todo.model.Note;
import com.bridgeit.todo.model.User;

@Service("noteService")
public class NoteServiceImpl implements NoteService {
	
	@Autowired
	NoteDao noteDao;
	
	public int saveNotes(Note note){
		return  noteDao.saveNotes(note);
		
	}

	@Override
	public void deleteNoteById(int noteId) {
		noteDao.deleteNoteById(noteId);
		
	}

	public List<Note> findAllNote(User user) {
		return noteDao.findAllNote(user);
		
	}

	public void updateNote(Note note) {
		noteDao.updateNote(note);
		
	}
	@Override
	public Note getNoteById(int id) {
		return noteDao.getNoteById(id);
	}
	@Override
	public List<Note> getSharedNotes(int id) {
		return noteDao.getSharedNotes(id);
		 
	}

	@Override
	public void removeCollabeUser(Note oldNote, User user) {
		noteDao.removeCollabeUser(oldNote, user);
	}

	@Override
	public Label createLabel(User user, Label label) {
		return noteDao.createLabel( user, label);
		
	}

	@Override
	public boolean deleteLabel(Label label, User user) {
		
		return noteDao.deleteLabel(label, user);
	}

	@Override
	public List<Note> getNotesForTrash() {
		
		return noteDao.getNotesForTrash();
	}

}
