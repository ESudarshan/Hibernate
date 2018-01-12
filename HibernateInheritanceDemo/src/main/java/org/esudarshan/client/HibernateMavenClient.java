package org.esudarshan.client;

import org.esudarshan.model.FourWheeler;
import org.esudarshan.model.TwoWheeler;
import org.esudarshan.model.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateMavenClient {

	public static void main(String[] args) {

		System.out.println("Welcome");

		Vehicle vehicle = new Vehicle();
		vehicle.setName("Car");

		TwoWheeler twoWheeler = new TwoWheeler();
		twoWheeler.setName("Bike");
		twoWheeler.setSteeringHandle("Bike Steering Handle");

		FourWheeler fourWheeler = new FourWheeler();
		fourWheeler.setName("Porsche");
		fourWheeler.setSteeringWheel("Car Steering Wheel");

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(vehicle);
		session.save(twoWheeler);
		session.save(fourWheeler);
		session.getTransaction().commit();
		session.close();

		System.out.println("Thank you!");
	}
}
