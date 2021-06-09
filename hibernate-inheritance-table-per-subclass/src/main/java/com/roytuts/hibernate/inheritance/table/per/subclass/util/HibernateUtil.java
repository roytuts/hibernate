package com.roytuts.hibernate.inheritance.table.per.subclass.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.roytuts.hibernate.inheritance.table.per.subclass.entity.Person;
import com.roytuts.hibernate.inheritance.table.per.subclass.entity.Student;
import com.roytuts.hibernate.inheritance.table.per.subclass.entity.Teacher;

public final class HibernateUtil {

	private HibernateUtil() {
	}

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			try {
				Configuration configuration = configuration();
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return sessionFactory;
	}

	private static Configuration configuration() {
		Properties settings = new Properties();

		settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		settings.put(Environment.URL, "jdbc:mysql://localhost:3306/roytuts");
		settings.put(Environment.USER, "root");
		settings.put(Environment.PASS, "root");
		settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
		settings.put(Environment.SHOW_SQL, "true");
		settings.put(Environment.FORMAT_SQL, "true");
		settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		settings.put(Environment.HBM2DDL_AUTO, "update"); // or create
		// Deprecated
		// settings.put(Environment.QUERY_TRANSLATOR,
		// "org.hibernate.hql.internal.classic.ClassicQueryTranslatorFactory");

		Configuration configuration = new Configuration();

		configuration.setProperties(settings);
		configuration.addAnnotatedClass(Person.class);
		configuration.addAnnotatedClass(Student.class);
		configuration.addAnnotatedClass(Teacher.class);

		return configuration;
	}

}
