package com.bridgeit.todo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "label")
@Entity
public class Label {

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;

	@Column(name = "name")
	private String name;

	@JsonIgnore
	@ManyToMany(mappedBy="label", cascade=CascadeType.ALL)
	private Set notes = new HashSet<>();

	@JsonIgnore
	@ManyToOne
	private User user;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set getNotes() {
		return notes;
	}


	public void setNotes(Set notes) {
		this.notes = notes;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Label() {

	}


	@Override
	public String toString() {
		return "Label [id=" + id + ", name=" + name + ", notes=" + notes + ", user=" + user + "]";
	}

}
