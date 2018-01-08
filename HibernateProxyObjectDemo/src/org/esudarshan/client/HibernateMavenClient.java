package org.esudarshan.client;

import org.esudarshan.model.Address;
import org.esudarshan.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateMavenClient {

	public static void main(String[] args) {

		System.out.println("Welcome");

		User userOne = new User();
		userOne.setName("One");
		Address addressOne = new Address();
		addressOne.setCity("CityOne");
		addressOne.setState("StateOne");
		userOne.getAddresses().add(addressOne);
		Address addressTwo = new Address();
		addressTwo.setCity("CityTwo");
		addressTwo.setState("StateTwo");
		userOne.getAddresses().add(addressTwo);

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userOne);
		session.getTransaction().commit();
		session.close();

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			User user = new User();
			user = session.get(User.class, 1);
			System.out.println(user);
			session.close();
			// Eager Init
			for (Address address : user.getAddresses()) {
				System.out.println(address);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Thank you!");
		}
	}

}
