package com.bridgeit.todo.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.bridgeit.todo.model.User;

@Service("UserDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public int saveUser(User user) {
		String hashedPassword = null;
		System.out.println("User is :" + user);
		if (!(user.getPassword() == null)) {
			System.out.println("User pass is: " + user.getPassword());
			hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
			user.setPassword(hashedPassword);
			System.out.println("hashed password: " + hashedPassword);
		}

		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.persist(user);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return 1;
	}

	@SuppressWarnings("deprecation")
	public User userLogin(User user) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", user.getEmail()));
		criteria.add(Restrictions.eq("password", user.getPassword()));
		criteria.add(Restrictions.eq("isActivated", true));
		User finalUser = (User) criteria.uniqueResult();
		if (finalUser == null) {
			session.close();
			return null;
		}
		session.close();
		return finalUser;
	}

	@SuppressWarnings("deprecation")
	@Override
	public User getUserById(int id) {
		Session session = sessionFactory.openSession();
		// Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("id", id));
		User user = (User) criteria.uniqueResult();
		session.close();
		return user;
	}

	@Override
	@SuppressWarnings("deprecation")
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		User user = (User) criteria.uniqueResult();
		session.close();
		return user;
	}

	/*@Override
	public boolean setPassword(User user1) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("New Password: " + user1.getPassword());
		String hashedPassword = BCrypt.hashpw(user1.getPassword(), BCrypt.gensalt(10));
		user1.setPassword(hashedPassword);
		System.out.println("decrpt passwrd: " + user1.getPassword());
		try {
			//String pass1 = user1.getPassword();
			session.update(user1);
			System.out.println("after commit: Updated Password: " + user1.getPassword());
			String pass2 = user1.getPassword();
			if (pass1.equalsIgnoreCase(pass2))
				System.out.println("!!!!!!!*****!!!*!*!*!*!**!**");
			tx.commit();
		} catch (Exception e) {
			
		} finally {
			session.close();
		}
		
		return true;
	}
*/
	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(user);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return true;
	}
	
	public boolean setPassword(User user1) {
		Session session=sessionFactory.openSession();
		Transaction transaction=null;
		try
		{
			
		transaction=session.beginTransaction();
		String hashedPassword = BCrypt.hashpw(user1.getPassword(), BCrypt.gensalt(10));
		user1.setPassword(hashedPassword);
		System.out.println(""+hashedPassword);
		String hql="update User set password=:password where id=:id";
		
		Query query=session.createQuery(hql);
		query.setParameter("password",hashedPassword);
		query.setParameter("id", user1.getId());
		query.executeUpdate();
		
		transaction.commit();
		session.close();
		return true;
		}
		
		catch (Exception e) {
		if(transaction!=null)
		transaction.rollback();
		e.printStackTrace();
		}
		return false;
		}
	
}
