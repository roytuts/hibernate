package com.roytuts.hibernate.evict.method.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class EvictMethodTest {

	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			Employee employee = new Employee();
			employee.setName("Soumitra");
			employee.setDesignation("Designation");
			employee.setJoiningdate("18-Dec-2018");
			employee.setPhone("1234567890");

			session.save(employee);

			System.out.println("Employee saved: " + employee);
			System.out.println();

			Employee employee1 = (Employee) session.load(Employee.class, 6l);

			System.out.println("Before evict() session contains employee1 : " + session.contains(employee1));

			session.evict(employee1);
			System.out.println("After evict() session contains employee1 : " + session.contains(employee1));

		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			transaction.commit();
			if (session != null) {
				HibernateUtil.closeSession();
			}
		}

	}

}
