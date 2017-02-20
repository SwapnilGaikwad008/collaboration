package com.niit.collaboration.DAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.DAO.UserDAO;
import com.niit.collaboration.model.User;

public class UserDAOImpl implements UserDAO {
	
	@Autowired
	public SessionFactory sessionFactory;
	
	@Autowired
	public User user;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	public boolean SaveUser(User user) {
		try{
			sessionFactory.getCurrentSession().save(user);
		}catch(Exception e){
			
		}
		return false;
	}

	@Transactional
	public boolean UpdateUser(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
		} catch (Exception e) {
					}
		return false;
	}


	@Transactional
	public boolean DeleteUser(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
		} catch (Exception e) {
			
		}
		return false;
	}

	@Transactional
	public User getById(String emailId) {
				return (User) sessionFactory.getCurrentSession().get(User.class, emailId);
	}

	@Transactional
	public List<User> list() {
		String hql="From User";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public List<User> getAllList(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	public void setOnline(String username) {
		String hql="UPDATE User SET isOnline = 'Y' where username= '"+ username +"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();		
	}


	@Transactional
	public void setOffline(String username) {
		String hql="UPDATE User SET isOnline = 'N' where username= '"+username+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
		
	}


	@Transactional
	public User isValidUser(String emailid, String password) {
		
		try {
			String hql="From User where emailid=:emailid and password=:password";
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
			query.setString("emailid", emailid);
			query.setString("password",password);
			return (User) query.uniqueResult();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
