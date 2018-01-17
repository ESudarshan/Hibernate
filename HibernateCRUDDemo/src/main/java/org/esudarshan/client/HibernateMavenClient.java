package org.esudarshan.client;

import org.esudarshan.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateMavenClient {

	public static void main(String[] args) {

		System.out.println("Welcome");

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		// Create
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for (int i = 0; i < 5; i++) {
			User user = new User();
			user.setName("User_" + i);
			session.save(user);
		}
		session.getTransaction().commit();
		session.close();

		// Read
		for (int i = 1; i <= 5; i++) {
			read(sessionFactory, i);
		}

		// Update
		session = sessionFactory.openSession();
		session.beginTransaction();
		User user = session.get(User.class, 5);
		user.setName("User_" + 5);
		session.update(user);
		session.getTransaction().commit();
		session.close();

		// Read
		read(sessionFactory, 5);

		// Delete
		session = sessionFactory.openSession();
		session.beginTransaction();
		user = session.get(User.class, 1);
		session.delete(user);
		session.getTransaction().commit();
		session.close();

		// Read
		read(sessionFactory, 1);

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
