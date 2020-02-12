package com.roytuts.hibernate.criteria.api;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.roytuts.hibernate.criteria.api.entity.Item;
import com.roytuts.hibernate.criteria.api.utils.HibernateUtil;

public class HibernateCriteriaApp {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();

		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Item> criteria = builder.createQuery(Item.class);

		// return result list
		// criteria.from(Item.class);
		// List<Item> items = session.createQuery(criteria).getResultList();
		// items.stream().forEach(i -> System.out.println(i));

		// return max 3 results
		// List<Item> items =
		// session.createQuery(criteria).setMaxResults(3).getResultList();
		// items.stream().forEach(i -> System.out.println(i));

		// return result for item description having value CD
		// Root<Item> root = criteria.from(Item.class);
		// criteria.where(builder.like(root.get("desc"), "%CD%"));
		// List<Item> items = session.createQuery(criteria).getResultList();
		// items.stream().forEach(i -> System.out.println(i));

		// return result for item id having value between min and max
		// Root<Item> root = criteria.from(Item.class);
		// criteria.where(builder.between(root.get("id"), 2, 4));
		// List<Item> items = session.createQuery(criteria).getResultList();
		// items.stream().forEach(i -> System.out.println(i));

		// return result in ascending order
		// Root<Item> root = criteria.from(Item.class);
		// criteria.orderBy(builder.asc(root.get("price")));
		// List<Item> items = session.createQuery(criteria).getResultList();
		// items.stream().forEach(i -> System.out.println(i));

		// return result in descending order
		// Root<Item> root = criteria.from(Item.class);
		// criteria.orderBy(builder.desc(root.get("id")));
		// List<Item> items = session.createQuery(criteria).getResultList();
		// items.stream().forEach(i -> System.out.println(i));

		// return result by equality check
		Root<Item> root = criteria.from(Item.class);
		criteria.where(builder.equal(root.get("name"), "CD"));
		List<Item> items = session.createQuery(criteria).getResultList();
		items.stream().forEach(i -> System.out.println(i));

		tx.commit();
	}

}
