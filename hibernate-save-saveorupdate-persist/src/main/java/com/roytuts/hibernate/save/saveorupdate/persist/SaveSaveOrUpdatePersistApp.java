package com.roytuts.hibernate.save.saveorupdate.persist;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.roytuts.hibernate.save.saveorupdate.persist.entity.Product;
import com.roytuts.hibernate.save.saveorupdate.persist.util.HibernateUtils;

public class SaveSaveOrUpdatePersistApp {

	public static void main(String[] args) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();

		Product product = new Product("Sample Product", "SP", 553.00);

		// Integer id = (Integer) session.save(product);
		// System.out.println("Id: " + id);

		// session.saveOrUpdate(product);
		
		session.persist(product);

		tx.commit();
	}

}
