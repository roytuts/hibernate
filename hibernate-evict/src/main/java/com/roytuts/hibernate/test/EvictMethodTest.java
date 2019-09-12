package com.roytuts.hibernate.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.roytuts.hibernate.domain.model.Employee;
import com.roytuts.hibernate.util.HibernateUtil;

public class EvictMethodTest {

	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Employee employee = new Employee();
			employee.setName("Soumitra");
			employee.setDesignation("Software Engineer");
			employee.setJoiningdate("18-Dec-2013");
			employee.setPhone("1234567890");
			session.save(employee);
			transaction.commit();

			System.out.println("Employee saved: " + employee);

			System.out.println();

			Employee employee1 = (Employee) session.load(Employee.class, new Long(1));
			System.out.println("Before evict() session contains employee1 : " + session.contains(employee1));

			session.evict(employee1);
			System.out.println("After evict() session contains employee1 : " + session.contains(employee1));
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				HibernateUtil.closeSession();
			}
		}

	}

}
