package com.bridgeit.todo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

public class Collaborator {

	@Id
	@GenericGenerator(name = "collab", strategy = "increment")
	@GeneratedValue(generator = "collab")
	private int collabId;
	
	@ManyToOne
	@JoinColumn
	private User ownerId;
	
	@ManyToOne
	@JoinColumn
	private User shareId;
	
	@ManyToOne
	@JoinColumn
	private Note noteId;

	public int getCollabId() {
		return collabId;
	}

	public void setCollabId(int collabId) {
		this.collabId = collabId;
	}

	public User getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(User ownerId) {
		this.ownerId = ownerId;
	}

	public User getShareId() {
		return shareId;
	}

	public void setShareId(User shareId) {
		this.shareId = shareId;
	}

	public Note getNoteId() {
		return noteId;
	}

	public void setNoteId(Note noteId) {
		this.noteId = noteId;
	}
}
