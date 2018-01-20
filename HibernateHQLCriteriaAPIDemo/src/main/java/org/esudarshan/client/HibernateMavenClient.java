package org.esudarshan.client;

import java.util.List;
import org.esudarshan.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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

		// Criteria
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("id", 7));
		List<User> userList = (List<User>) criteria.list();
		for (User user : userList) {
			System.out.println(user);
		}

		// Restrictions : OR
		criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.or(Restrictions.like("name", "User-1%"), Restrictions.le("id", 5),
				Restrictions.between("id", 5, 7)));
		criteria.addOrder(Order.desc("id"));
		userList = (List<User>) criteria.list();
		for (User user : userList) {
			System.out.println(user);
		}

		// Projections for projecting column
		criteria = session.createCriteria(User.class);
		criteria.setProjection(Projections.property("name"));
		List<String> userNameList = (List<String>) criteria.list();
		for (String userName : userNameList) {
			System.out.println(userName);
		}

		// Projections for Aggregate Functions
		criteria = session.createCriteria(User.class);
		criteria.setProjection(Projections.max("id"));
		System.out.println("Max ID : " + criteria.uniqueResult());
		criteria.setProjection(Projections.min("id"));
		System.out.println("Min ID : " + criteria.uniqueResult());
		criteria.setProjection(Projections.count("id"));
		System.out.println("Count ID : " + criteria.uniqueResult());

		// Example to avoid Criteria.add for multiple fields

		// Simple Search
		User userToBeSearched = new User();
		userToBeSearched.setName("User-7");
		Example example = Example.create(userToBeSearched);
		criteria = session.createCriteria(User.class);
		criteria.add(example);
		userList = (List<User>) criteria.list();
		for (User user : userList) {
			System.out.println(user);
		}

		// Enable Like Search
		userToBeSearched = new User();
		userToBeSearched.setName("User-1%");
		example = Example.create(userToBeSearched).enableLike();// .excludeProperty("propertyNotToBeFilteredOn");
		criteria = session.createCriteria(User.class);
		criteria.add(example);
		userList = (List<User>) criteria.list();
		for (User user : userList) {
			System.out.println(user);
		}

		session.getTransaction().commit();
		session.close();

		System.out.println("Thank you!");
	}

}
