package edu.mum.cs.cs544.exercises;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class AppCar {

    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void main(String[] args) {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            
            Customer customer1 = new Customer();
            Customer customer2 = new Customer();
            Customer customer3 = new Customer();
            
            Address address1 = new Address();
            address1.setCity("Karachi");
            address1.setState("Sindh");
            address1.setCustomer(customer1);
            
            Address address2 = new Address();
            address1.setCity("Lahore");
            address1.setState("Punjab");
            address1.setCustomer(customer2);
            
            
            customer1.setFirstName("Ali");
            customer1.setLastName("Raza");
            customer1.setAddress(address1);
            
            
            customer2.setFirstName("Adnan");
            customer1.setLastName("Shehzad");
            customer1.setAddress(address2);
            
            
            
           
            session.persist(customer1);
            session.persist(customer2);          
            session.persist(address1);
            session.persist(address2);
            
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }


        
        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
        System.exit(0);
    }
}
