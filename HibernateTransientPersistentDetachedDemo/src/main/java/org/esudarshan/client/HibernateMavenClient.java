package org.esudarshan.client;

import org.esudarshan.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateMavenClient {

	public static void main(String[] args) {

		System.out.println("Welcome");

		// Transient
		User user = new User();
		user.setName("Transient User");

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Persistent
		session.save(user);
		user.setName("Persistent User");
		user.setName("Again Persistent User");

		session.getTransaction().commit();
		session.close();

		// Detached
		user.setName("Detached User");

		System.out.println("Thank you!");

	}

	public static User read(SessionFactory sessionFactory, int index) {
		Session session = null;
		User user = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			user = new User();
			user = session.get(User.class, index);
			System.out.println(user);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
