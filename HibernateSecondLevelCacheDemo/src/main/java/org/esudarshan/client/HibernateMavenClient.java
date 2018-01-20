package org.esudarshan.client;

import org.esudarshan.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateMavenClient {

	public static void main(String[] args) {

		System.out.println("Welcome");

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setName("User-" + (i + 1));
			session.save(user);
		}

		session.getTransaction().commit();
		session.close();

		Session session1 = sessionFactory.openSession();
		session1.beginTransaction();
		User user1 = session1.get(User.class, 7);
		System.out.println(user1);
		session1.getTransaction().commit();
		session1.close();

		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		User user2 = session2.get(User.class, 7);
		System.out.println(user2);
		session2.getTransaction().commit();
		session2.close();

		System.out.println("Thank you!");
	}

}
