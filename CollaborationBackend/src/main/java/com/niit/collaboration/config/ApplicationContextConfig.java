package com.niit.collaboration.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Chat;
import com.niit.collaboration.model.Job;
import com.niit.collaboration.model.User;

@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class ApplicationContextConfig {

	@Autowired
	@Bean(name = "dataSource")
	public DriverManagerDataSource getDataSource() {
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
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.show.sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		return properties;
	}

	@Autowired
	@Bean(name="sessionfactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder sessionBuilder =new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Chat.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(User.class);
		
		
        return sessionBuilder.buildSessionFactory();
		
	}
	@Autowired
	@Bean("name=transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
}
