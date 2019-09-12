package com.roytuts.hibernate.second.level.ehcache;

import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

import com.roytuts.hibernate.second.level.ehcache.entity.Cd;
import com.roytuts.hibernate.second.level.ehcache.util.HibernateUtil;

public class EhCacheApp {

	public static void main(String[] args) {
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		Session sess = null;
		Session dbSession1 = null;
		Session dbSession2 = null;
		Session newSession = null;
		Transaction tran = null;
		Transaction dbTransaction1 = null;
		Transaction dbTransaction2 = null;
		Transaction newTransaction = null;

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Statistics statistics = sessionFactory.getStatistics();

		System.out.println("Fetch count from DB: " + statistics.getEntityFetchCount());
		System.out.println("Fetch count from second-level session: " + statistics.getSecondLevelCacheHitCount());
		System.out.println("Miss count from second-level session: " + statistics.getSecondLevelCacheMissCount());
		System.out.println("Put count from second-level session:" + statistics.getSecondLevelCachePutCount());
		System.out.println();
		try {
			// save the new CD
			sess = sessionFactory.openSession();
			tran = sess.beginTransaction();
			Cd cd = new Cd();
			cd.setTitle("CD Caching");
			cd.setArtist("Soumitra");
			sess.save(cd);
			tran.commit();
			sess.close();

			dbSession1 = sessionFactory.openSession();
			dbTransaction1 = dbSession1.beginTransaction();

			// load the CD from database table
			Cd cd1 = (Cd) dbSession1.load(Cd.class, 4l);
			System.out.println("Cd Loaded from DB - title: " + cd1.getTitle());
			System.out.println();

			// load the CD from first-level Session
			Cd cd2 = (Cd) dbSession1.load(Cd.class, 4l);
			System.out.println("Cd Loaded from First-level session - title: " + cd2.getTitle());
			System.out.println();

			dbTransaction1.commit();
			dbSession1.close();

			System.out.println("Fetch count from DB: " + statistics.getEntityFetchCount());
			System.out.println("Fetch count from second-level session: " + statistics.getSecondLevelCacheHitCount());
			System.out.println("Miss count from second-level session: " + statistics.getSecondLevelCacheMissCount());
			System.out.println("Put count from second-level session:" + statistics.getSecondLevelCachePutCount());
			System.out.println();

			System.out.println("Waiting........");
			System.out.println();
			Thread.sleep(5000);

			dbSession2 = sessionFactory.openSession();
			dbTransaction2 = dbSession2.beginTransaction();

			// load the Cd from Database
			Cd cd3 = (Cd) dbSession2.load(Cd.class, 4l);
			System.out.println();
			System.out.println("Again Loaded Cd from DB - title: " + cd3.getTitle());
			System.out.println();

			dbTransaction2.commit();
			dbSession2.close();

			System.out.println("Fetch count from DB: " + statistics.getEntityFetchCount());
			System.out.println("Fetch count from second-level session: " + statistics.getSecondLevelCacheHitCount());
			System.out.println("Miss count from second-level session: " + statistics.getSecondLevelCacheMissCount());
			System.out.println("Put count from second-level session:" + statistics.getSecondLevelCachePutCount());
			System.out.println();

			newSession = sessionFactory.openSession();
			newTransaction = newSession.beginTransaction();

			// we have already second-level of cache for the Cd
			Cd cd4 = (Cd) newSession.get(Cd.class, 4l);
			System.out.println("Cd loaded from Second-level - title: " + cd4.getTitle());
			System.out.println();
			newTransaction.commit();
			newSession.close();

			System.out.println("Fetch count from DB: " + statistics.getEntityFetchCount());
			System.out.println("Fetch count from second-level session: " + statistics.getSecondLevelCacheHitCount());
			System.out.println("Miss count from second-level session: " + statistics.getSecondLevelCacheMissCount());
			System.out.println("Put count from second-level session:" + statistics.getSecondLevelCachePutCount());
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			if (tran != null && sess.isOpen()) {
				tran.rollback();
			}
			if (dbTransaction1 != null && dbSession1.isOpen()) {
				dbTransaction1.rollback();
			}
			if (dbTransaction2 != null && dbSession2.isOpen()) {
				dbTransaction2.rollback();
			}
			if (newTransaction != null && newSession.isOpen()) {
				newTransaction.rollback();
			}
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
