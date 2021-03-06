package com.bridgeit.todo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.todo.model.Label;
import com.bridgeit.todo.model.Note;
import com.bridgeit.todo.model.User;

@Service("NoteDao")
public class NoteDaoImpl implements NoteDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

  /*/////////////////////////////// Create New Notes  ///////////////////////////////*/	
	@Override
	public int saveNotes(Note note) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(note);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return 1;

	}
	 /*/////////////////////////////// Delete Notes By Id ///////////////////////////////*/	
	@Override
	public void deleteNoteById(int noteId) {

		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Note note = new Note();
			note.setNoteId(noteId);
			session.delete(note);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
	}

 /*/////////////////////////////// Get All Notes ///////////////////////////////*/	
	
	/*@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Note> findAllNote(User user) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Note.class);
		criteria.add(Restrictions.eq("user", user));
		criteria.addOrder(Order.desc("modifiedDate"));
		List<Note> notes = criteria.list();
		System.out.println(notes);
		return notes;
	}*/

	@Override
	public List<Note> findAllNote(User user) {
		Session session = sessionFactory.openSession();
		User user1 = session.get(User.class, user.getId());
		List<Note> note = user1.getNote();
		System.out.println("=========>"+note.size());
		note.size();
		return note;
	}
	
	 
  /*/////////////////////////////// Update Notes ///////////////////////////////*/	
	public void updateNote(Note note) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {

			transaction = session.beginTransaction();
			session.saveOrUpdate(note);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
	}

 /*/////////////////////////////// Get Note By Id ///////////////////////////////*/
	public Note getNoteById(int noteId) {
		Session session = sessionFactory.openSession();
		Note note = session.get(Note.class, noteId);
		session.close();
		return note;
	}
 /*/////////////////////////////// Get User By Email ///////////////////////////////*/
	@SuppressWarnings({ "unchecked" })
	public User getUserByEmail(String email, User user) {
		Session session = sessionFactory.openSession();
		List<User> userList = new ArrayList<>();
		userList = session.createQuery("from User").getResultList();
		for (User tempUser : userList) {
			if (tempUser.getEmail().equalsIgnoreCase(email)) {
				user = tempUser;
				System.out.println("get uswer by email: " + user);
				return user;
			}
		}

		return user;
	}

	/*/////////////////////////////// Get List Of Collaborator User ///////////////////////////////*/
	@Override
	public List<Note> getSharedNotes(int id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Note.class);
		criteria.createAlias("sharedUser", "c");
		criteria.add(Restrictions.eq("c.id", id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Note> sharedNotes = criteria.list();
		session.close();
		return sharedNotes;
	}
	
	/*/////////////////////////////// Remove Collab User ///////////////////////////////*/
	@Override
	public Object removeCollabeUser(Note oldNote, User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		return null;
	}
	
	public List<Note> getNotesForTrash() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("From Note where trash=true");
		List<Note> notes = query.list();		
		session.close();
		return notes;
	}
	
	/*/////////////////////////////// add label ///////////////////////////////*/
	@Override
	public Label createLabel(User user, Label label) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		label.setLabelId(0);
		label.setUser(user);
		session.save(label);
		tx.commit();
		session.close();
		return label;
	}
	@Override
	public Set<Label> getAllLabel(int id) {
		return null;
	}

	/*/////////////////////////////// delete label ///////////////////////////////*/
	@Override
	public boolean deleteLabel(Label label, User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.delete(label );
			for(Note note : label.getNotes())
			{
				note.getLabels().remove(label);
			}
			label.getNotes().clear();
			transaction.commit();
		} catch (HibernateException e) {
			if(transaction!=null){
				transaction.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
		return false;
	}
}