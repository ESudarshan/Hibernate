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

		// Named Query
		Query query = session.getNamedQuery("USER.byId");
		query.setInteger(0, 1);
		List<User> userList = (List<User>) query.list();
		System.out.println(userList.get(0));

		// NamedNative Query
		query = session.getNamedQuery("USER.byName");
		query.setString(0, "User-7");
		userList = (List<User>) query.list();
		System.out.println(userList.get(0));

		session.getTransaction().commit();
		session.close();

		System.out.println("Thank you!");
	}

}
