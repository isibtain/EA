package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DoctorApp {

	
	private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void main(String[] args) throws ParseException {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
            
			/*
			 * Customer c1 = new Customer(); Customer c2 = new Customer(); Customer c3 = new
			 * Customer();
			 */
            
            Patient patient = new Patient();
            patient.setName("John Doe");
            patient.setCity("Boston");
            patient.setZip("23114");
            patient.setStreet("100 Main Street");

            Doctor doctor = new Doctor();

            doctor.setDoctorType("Eye doctor");
            doctor.setFirstname("Frank");
            doctor.setLastname("Brown");

            session.persist(doctor);

            Payment payment = new Payment();
            payment.setPaydate(df.parse("09/07/1992"));
            payment.setAmount(100.0);

            Appointment appointment = new Appointment();

            appointment.setDate(df.parse("07/07/1991"));
            appointment.setDoctor(doctor);
            appointment.setPatient(patient);
            appointment.setPayment(payment);
            
            session.persist(appointment);
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

}

	}
