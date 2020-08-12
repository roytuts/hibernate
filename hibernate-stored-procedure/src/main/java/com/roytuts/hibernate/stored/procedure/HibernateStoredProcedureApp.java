package com.roytuts.hibernate.stored.procedure;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.roytuts.hibernate.stored.procedure.entity.Cd;
import com.roytuts.hibernate.stored.procedure.util.HibernateUtil;

public class HibernateStoredProcedureApp {

	public static void main(String[] args) {
		HibernateStoredProcedureApp sp = new HibernateStoredProcedureApp();

		Cd cd1 = sp.getCdByNativeSQL(1);
		System.out.println("Title: " + cd1.getTitle());
		System.out.println("Interpret: " + cd1.getArtist());

		Cd cd2 = sp.getCdByNamedQuery(4);
		System.out.println("Title: " + cd2.getTitle());
		System.out.println("Interpret: " + cd2.getArtist());
	}

	public Cd getCdByNativeSQL(long cdId) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Cd cd = (Cd) session.createNativeQuery("CALL getCds(:cdId)").addEntity(Cd.class).setParameter("cdId", cdId)
					.getSingleResult();
			return cd;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public Cd getCdByNamedQuery(long cdId) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Cd cd = (Cd) session.getNamedNativeQuery("getCds").setParameter("cdId", cdId).getSingleResult();
			return cd;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tx.commit();
			session.close();
		}
		return null;
	}

}
