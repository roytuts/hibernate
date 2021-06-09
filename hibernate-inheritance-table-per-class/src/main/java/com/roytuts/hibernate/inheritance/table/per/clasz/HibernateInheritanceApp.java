package com.roytuts.hibernate.inheritance.table.per.clasz;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.roytuts.hibernate.inheritance.table.per.clasz.entity.Student;
import com.roytuts.hibernate.inheritance.table.per.clasz.entity.Teacher;
import com.roytuts.hibernate.inheritance.table.per.clasz.util.HibernateUtil;

public class HibernateInheritanceApp {

	public static void main(String[] args) {

		Student s = new Student();
		Teacher t = new Teacher();

		s.setId(1);
		s.setName("Student");
		s.setYear("1st Year");

		t.setId(2);
		t.setName("Teacher");
		t.setSubject("Physics");

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.save(s);
			session.save(t);

			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}

	}

}
