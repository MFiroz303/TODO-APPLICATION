package com.bridgeit.todo.dao;

import com.bridgeit.todo.model.Label;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public void deleteNoteById(int id) {

		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Note note = new Note();
			note.setNoteId(id);
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

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Note> findAllNote(User user) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Note.class);
		criteria.add(Restrictions.eq("user", user));
		criteria.addOrder(Order.desc("modifiedDate"));
		List<Note> notes = criteria.list();
		System.out.println(notes);
		return notes;
	}

	/*
	 * @SuppressWarnings({ "unchecked", "deprecation" })
	 * 
	 * @Override public List<Note> findAllNote(User user) { Session session =
	 * sessionFactory.openSession(); Criteria criteria =
	 * session.createCriteria(Note.class); criteria.add(Restrictions.eq("user",
	 * user)) .addOrder(Order.desc("modifiedDate"))
	 * .setResultTransformer(Transformers.aliasToBean(Note.class)); List<Note>
	 * notes = criteria.list(); System.out.println(notes); return notes; }
	 */

	public void updateNote(Note note) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {

			transaction = session.beginTransaction();
			/*
			 * Note note1 = session.byId(Note.class).load(id);
			 * note1.setTitle(note.getTitle());
			 * note1.setDescription(note.getDescription());
			 * note1.setModifiedDate(note.getModifiedDate());
			 * session.update(note1);
			 */

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

	public Note getNoteById(int noteId) {
		Session session = sessionFactory.openSession();
		Note note = session.get(Note.class, noteId);
		session.close();
		return note;
	}

	@SuppressWarnings({ "unchecked" })
	public User getUserByEmail(String email, User user) {
		Session session = sessionFactory.openSession();
		List<User> userList = new ArrayList<>();
		// jpa
		/*
		 * CriteriaBuilder builder = session.getCriteriaBuilder();
		 * CriteriaQuery<User> criteria = builder.createQuery(User.class);
		 */
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

	@Override
	public List<Note> getSharedNotes(int noteId) {
		Session session = sessionFactory.openSession();
		Note note = session.get(Note.class, noteId);

		Criteria criteria = session.createCriteria(Note.class);
		criteria.createAlias("sharedUser", "c");
		criteria.add(Restrictions.eq("sharedUser", noteId));
		List<Note> sharedNotes = criteria.list();

		session.close();
		return sharedNotes;
	}
	@Override
	public Object removeCollabeUser(Note oldNote, User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		return null;
	}
	
	@Override
	public Label createLabel(User user, Label label) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		label.setId(label.getId());
		label.setUser(user);
		session.save(label);
		tx.commit();
		session.close();
		return label;
	}
	/*@Override
	public List<Label> getLabels(User user) {

		logger.info("Into getLabels()");
		System.out.println("User is: " + user);
		Session session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Label> criteria = builder.createQuery(Label.class);
		criteria.from(Label.class);
		List<Label> entireLabelList = session.createQuery(criteria).getResultList();
		// learn a more efficient way to retrieve notes
		List<Label> labelList = new ArrayList<>();

		// retrieve labels of which user is owner
		if (entireLabelList.size() != 0)

			for (Label tempLabel : entireLabelList)
				if (tempLabel.getUser().getId().compareTo(user.getId()) == 0) {
					labelList.add(tempLabel);
				}
		return labelList;
	}*/
}