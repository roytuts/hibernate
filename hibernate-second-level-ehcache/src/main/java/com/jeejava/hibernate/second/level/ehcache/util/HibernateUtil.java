package com.jeejava.hibernate.second.level.ehcache.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	public static SessionFactory getSessionFactory() {
		try {
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml").build();

			Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();

			return metadata.getSessionFactoryBuilder().build();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void closeSession() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

}
