package com.niit.collaboration.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.BlogComment;
import com.niit.collaboration.model.Event;
import com.niit.collaboration.model.FileUpload;
import com.niit.collaboration.model.Forum;
import com.niit.collaboration.model.ForumReply;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.JobApplied;
import com.niit.collaboration.model.User;


@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class ApplicationContextConfiguration
{
	
		@Bean(name ="dataSource")
		public DataSource getDataSource()
		{
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			
			dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
			dataSource.setUsername("COLB_DB");
			dataSource.setPassword("root");
			
			return dataSource;
		}
		
		private Properties getHibernateProperties()
		{
			Properties properties = new Properties();
			
			properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
	//		properties.put("hibernate.hbm2ddl.auto", "update");
			properties.put("hibernate.show_sql", "true");
			
			return properties;
		}
		
		@Autowired
		
		@Bean(name = "sessionFactory")
		public SessionFactory getSessionFactory(DataSource dataSource)
		{
			LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
			
			sessionBuilder.addProperties(getHibernateProperties());
			sessionBuilder.addAnnotatedClass(User.class);
			sessionBuilder.addAnnotatedClass(Blog.class);
			sessionBuilder.addAnnotatedClass(Job.class);
			sessionBuilder.addAnnotatedClass(JobApplied.class);
			sessionBuilder.addAnnotatedClass(Event.class);
			sessionBuilder.addAnnotatedClass(BlogComment.class);
			sessionBuilder.addAnnotatedClass(Friend.class);
		    sessionBuilder.addAnnotatedClass(FileUpload.class);
		    sessionBuilder.addAnnotatedClass(Forum.class);
		    sessionBuilder.addAnnotatedClass(ForumReply.class);
		    

			return sessionBuilder.buildSessionFactory();
		}
		
		@Autowired
		@Bean(name = "transactionManager")
		public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
		{
			HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
			return transactionManager;
		}
		
		@Bean
		public JavaMailSenderImpl javaMailSenderImpl(){
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost("smtp.gmail.com");
			mailSender.setPort(587);
			mailSender.setUsername("swapgaik9811@gmail.com");
			mailSender.setPassword("swapnil123");
			Properties prop = mailSender.getJavaMailProperties();
			prop.put("mail.transport.protocol", "smtp");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.debug", "true");
			return mailSender;
		}
		
}