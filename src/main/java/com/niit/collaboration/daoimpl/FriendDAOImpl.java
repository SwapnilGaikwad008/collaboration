package com.niit.collaboration.daoimpl;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.model.Friend;



@SuppressWarnings("deprecation")
@Repository("friendDAO")
@EnableTransactionManagement
public class FriendDAOImpl implements FriendDAO
{
	private static final Logger log = LoggerFactory.getLogger(FriendDAOImpl.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	public FriendDAOImpl(SessionFactory sessionFactory)
	{
		try 
		{
			this.sessionFactory = sessionFactory;
			log.info("Connection Established Successfully");
		} 
		catch (Exception ex) 
		{
			log.error("Failed to establish connection");
			ex.printStackTrace();
		}
	}
	
	private Integer getMaxId()
	{
		log.info("->->Starting of the method getMaxId");

		String hql = "select max(id) from Friend";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Integer maxID;
		try 
		{
			maxID = (Integer) query.uniqueResult();
			if(maxID==null)
			{
				maxID = 10000;
			}
		} catch (Exception e) 
		{
			log.error("Error getting Max ID");
			e.printStackTrace();
			return 10000;
		}
		log.info("Max id :" + maxID);
		return maxID;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	public List<Friend> getFriendList(String username) 
	{
		log.info("Entering Get Friend List for "+username);
		try
		{
			String sql01 = "FROM Friend where userID= '"+username+"' and status ='A'";
			String sql02 = "FROM Friend where friendID= '"+username+"' and status ='A'";
			Query query1 = sessionFactory.getCurrentSession().createQuery(sql01);
			Query query2 = sessionFactory.getCurrentSession().createQuery(sql02);
			
			List<Friend> list1 = query1.list();
			List<Friend> list2 = query2.list();
			list1.addAll(list2);
			log.info("Friend List Retrieved");
			return list1;
			
		}	catch(Exception ex)
		{
			log.error("Error getting friendList");
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes" })
	@Transactional
	public boolean get(String userID, String friendID) 
	{
		try
		{
			String sql  = "FROM Friend WHERE userID= '"+userID+"' and friendID = '"+friendID+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			System.out.println("This - "+query.list());
				if(query.list().isEmpty())
				{
					String sql2  = "FROM Friend WHERE friendID= '"+userID+"' and userID = '"+friendID+"'";
					Query query2 = sessionFactory.getCurrentSession().createQuery(sql2);
					if(query2.list().isEmpty())
					{	
						log.info("Friend Column is not available......");
						return true;
					}
				}
			log.info("Friend column found");
			return false;
		}	
		catch(Exception e)
		{
			log.error("Friend Column is not available.");
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean save(Friend friend) 
	{
		log.info("Add Friend");
		
		try
		{
			Integer maxID = getMaxId();
			friend.setId(maxID+1);
			sessionFactory.getCurrentSession().save(friend);
			
			log.info("Add friend success");
			return true;
		} catch (HibernateException e)
		{
			log.error("Error adding friend");
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public boolean accept(String userID, String friendID)
	{
		log.info("Update Friend");
		try
		{	
			String sql = "UPDATE Friend SET status = 'A' where userID = '"+friendID+"' and friendID = '"+userID+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.executeUpdate();
			log.info("Update friend success");
			return true;
		} catch (HibernateException e)
		{
			log.error("Error updating friend");
			e.printStackTrace();
			return false;
		}	
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public boolean reject(String userID, String friendID) 
	{
		log.info("Delete Friend");
		try
		{	
			String sql = "DELETE FROM Friend where userID = '"+friendID+"' and friendID = '"+userID+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.executeUpdate();
			log.info("Delete Success");
			return true;
		} catch (HibernateException e)
		{
			log.error("Error removing friend");
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional
	public boolean cancel(String userID, String friendID) 
	{
		log.info("Delete Friend");
		try
		{	
			String sql = "DELETE FROM Friend where userID = '"+userID+"' and friendID = '"+friendID+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(sql);
			query.executeUpdate();
			log.info("Delete Success");
			return true;
		} catch (HibernateException e)
		{
			log.error("Error removing friend");
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public List<Friend> showPendingRequests(String userID) 
	{
		log.info("Entering Pending Friend Request for "+userID);
		try
		{
			String sql = "from Friend where friendID= '"+userID+"' and status ='P'";
			
			@SuppressWarnings("unchecked")
			List<Friend> list = sessionFactory.openSession().createQuery(sql).list();
			log.info("Pending List Retrieved");
			return list;
			
		}	catch(Exception ex)
		{
			log.error("Error getting Pending List");
			ex.printStackTrace();
		}
		return null;
	}

	@Transactional
	public List<Friend> viewSentRequests(String userID) 
	{
		log.info("Entering Sent Friend Request for "+userID);
		try
		{
			String sql = "from Friend where userID= '"+userID+"' and status ='P'";
			
			@SuppressWarnings("unchecked")
			List<Friend> list = sessionFactory.openSession().createQuery(sql).list();
			log.info("Sent List Retrieved");
			return list;
			
		}	catch(Exception ex)
		{
			log.error("Error getting Sent List");
			ex.printStackTrace();
		}
		return null;	
	}
	
	@Transactional
	public boolean removeFriend(String userID, String friendID)
	{
		log.info("Remove Friend");
		try
		{	
			boolean a = cancel(userID, friendID);
			boolean b = reject(userID, friendID);
			if(a==true || b==true)
			{
				log.info("Delete Success"+a+" "+b);
				return true;
				
			}	return false;
			
		}	catch(HibernateException e)
		{
			log.error("Error Removing Friend");
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	public boolean setUsersOnline(String username)
	{
		String sql1 = "UPDATE Friend set userIsOnline = 'Y' where userID = '"+username+"'";
		String sql2 = "UPDATE Friend set friendisOnline = 'Y' where friendID = '"+username+"'";
		try
		{
			Query query1 = sessionFactory.getCurrentSession().createQuery(sql1);
			query1.executeUpdate();
			Query query2 = sessionFactory.getCurrentSession().createQuery(sql2);
			query2.executeUpdate();
			log.info("Online Update Success after login");
			return true;
		} catch(Exception e)
		{
			log.error("Error Updating Status");
			e.printStackTrace();
		}
		return false;
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional
	public boolean setUsersOffline(String username)
	{
		String sql1 = "UPDATE Friend set userIsOnline = 'N' where userID = '"+username+"'";
		String sql2 = "UPDATE Friend set friendisOnline = 'N' where friendID = '"+username+"'";
		try
		{
			Query query1 = sessionFactory.getCurrentSession().createQuery(sql1);
			query1.executeUpdate();
			Query query2 = sessionFactory.getCurrentSession().createQuery(sql2);
			query2.executeUpdate();
			log.info("Online Update Success after login");
			return true;
		} catch(Exception e)
		{
			log.error("Error Updating Status");
			e.printStackTrace();
		}
		return false;
	}
}