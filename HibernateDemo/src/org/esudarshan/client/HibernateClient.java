package org.esudarshan.client;

import java.util.Date;

import org.esudarshan.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateClient {

	public static void main(String[] args) {

		System.out.println("Welcome");

		User user = new User();
		user.setId(1);
		user.setName("One");
		user.setJoinedDate(new Date());
		user.setAddress("Pune");
		user.setDescription("Description goes here");

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();

		try {
			user = null;
			session = sessionFactory.openSession();
			session.beginTransaction();
			user = session.get(User.class, 1);
			System.out.println(user);
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Thank you!");
		}
	}

}
