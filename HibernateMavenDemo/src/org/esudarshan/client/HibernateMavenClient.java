package org.esudarshan.client;

import java.util.Date;

import org.esudarshan.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateMavenClient {

	public static void main(String[] args) {

		System.out.println("Welcome");

		User userOne = new User();
		// userOne.setId(1);
		userOne.setName("One");
		userOne.setJoinedDate(new Date());
		userOne.setAddress("Pune");
		userOne.setDescription("Description goes here");

		User userTwo = new User();
		// userTwo.setId(2);
		userTwo.setName("Two");
		userTwo.setJoinedDate(new Date());
		userTwo.setAddress("Pune");
		userTwo.setDescription("Description goes here");

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userOne);
		session.save(userTwo);
		session.getTransaction().commit();
		session.close();

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			for (int i = 1; i < 3; i++) {
				User user = new User();
				user = session.get(User.class, i);
				System.out.println(user);
			}
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Thank you!");
		}
	}

}
