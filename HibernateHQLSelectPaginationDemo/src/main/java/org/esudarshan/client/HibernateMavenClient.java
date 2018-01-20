package org.esudarshan.client;

import java.util.List;
import java.util.Map;

import org.esudarshan.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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

		session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("select new map(id, name) from User");

		// Pagination
		query.setFirstResult(5);
		query.setMaxResults(2);

		List<Map<String, String>> userList = (List<Map<String, String>>) query.list();
		System.out.println("No. of Users : " + userList.size());

		session.getTransaction().commit();
		session.close();

		for (Map<String, String> user : userList) {
			System.out.println(user);
		}

		System.out.println("Thank you!");
	}

}
