package com.niit.collaboration.daoimpl;


import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.FileUploadDAO;
import com.niit.collaboration.model.FileUpload;

@Repository("fileUploadDAO")
@EnableTransactionManagement
public class FileUploadDAOImpl implements FileUploadDAO
{
	private static final Logger log = LoggerFactory.getLogger(FileUploadDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public FileUploadDAOImpl(SessionFactory sessionFactory) 
	{
		try
		{
			log.info("Connection established successfully");
			this.sessionFactory = sessionFactory;
		}	catch(Exception e)
		{
			log.error("Error Getting Connection");
			e.printStackTrace();
		}
	}


	@SuppressWarnings("rawtypes")
	@Transactional
	public void save(FileUpload fileUpload, String username) 
	{
		try
		{
			String sql = "DELETE FROM FileUpload where username='"+username+"'";
			Query query =sessionFactory.getCurrentSession().createQuery(sql);
			query.executeUpdate();
			
			sessionFactory.getCurrentSession().save(fileUpload);
			log.info("File Uploaded");
		}	catch(Exception e)
		{
			log.error("Error uploading file");
			e.printStackTrace();
		}
	}

	@Transactional
	public FileUpload getFile(String username) 
	{
		String sql = "from FileUpload where username = '"+username+"'";
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		//query.setParameter(4, username);
        @SuppressWarnings("deprecation")
		FileUpload fileUpload=(FileUpload)query.setMaxResults(1).uniqueResult();
		//session.close();
		return fileUpload;
	}

}