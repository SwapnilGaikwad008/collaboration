package com.niit.collaboration.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.model.Friend;

@Repository("FriendDAO")
@EnableTransactionManagement
public class FriendDAOImpl implements FriendDAO{

	@Autowired
	SessionFactory sessionFactory;

	public FriendDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public void save(Friend friend) {
		
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(friend);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void update(Friend friend) {
		try {
			sessionFactory.getCurrentSession().update(friend);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void delete(String username, String friendId) {
      Friend friend = new Friend();
      friend.setUsername(username);
      friend.setFriendId(friendId);
      sessionFactory.getCurrentSession().delete(friend);      
	}
	@Transactional
	public void setOnline(String username) {
      String hql = "update Friend set isOnline = 'Y' where username = '"+username+"'";		
	  Query query = sessionFactory.getCurrentSession().createQuery(hql);
	  query.executeUpdate();
	}
	
	
	@Transactional
	public void setOffline(String username) {
		String hql = "update Friend set isOffline = 'N' where username = '" + username +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Transactional
	public List<Friend> getMyFriend(String username) {
		String hql1 = "select friendId from Friend where username='"+ username +"' and status ='A'";
	    String hql2 = "select username from Friend where friendId='"+ username +"' and status ='A'";
	    Query q1 = sessionFactory.openSession().createQuery(hql1);
	    Query q2 = sessionFactory.openSession().createQuery(hql2);
	    List<Friend> list1 = (List<Friend>)q1.list();
	    List<Friend> list2 = (List<Friend>)q2.list();	
	    return list1;    
	}

	
	@Transactional
	public List<Friend> getFriendRequestSentByMe(String username) {
		String hql ="select friendId from Friend where username ='" +username+"'and status = 'N'";
		Query query = sessionFactory.openSession().createQuery(hql);
		List<Friend> list = (List<Friend>)query.list();
		return list;
	}

	public List<Friend> getNewFriendRequest(String friendId) {
		String hql = "select username from Friend where friendId='"+friendId+ "'and status ='N'";
		Query query = sessionFactory.openSession().createQuery(hql);
		List<Friend> list = (List<Friend>)query.list();
		return list;
	}
	
	

}
