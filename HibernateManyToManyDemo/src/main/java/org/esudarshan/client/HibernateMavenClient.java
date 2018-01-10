package org.esudarshan.client;

import org.esudarshan.model.User;
import org.esudarshan.model.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateMavenClient {

	public static void main(String[] args) {

		System.out.println("Welcome");

		User userOne = new User();
		userOne.setName("One");
		Vehicle vehicleOne = new Vehicle();
		vehicleOne.setName("Car");
		userOne.getVehicles().add(vehicleOne);
		vehicleOne.getUsers().add(userOne);
		Vehicle vehicleTwo = new Vehicle();
		vehicleTwo.setName("Jeep");
		userOne.getVehicles().add(vehicleTwo);
		vehicleTwo.getUsers().add(userOne);

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userOne);
		session.save(vehicleOne);
		session.save(vehicleTwo);
		session.getTransaction().commit();
		session.close();

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			User user = new User();
			user = session.get(User.class, 1);
			System.out.println(user);
			System.out.println(user.getVehicles());
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Thank you!");
		}
	}

}
