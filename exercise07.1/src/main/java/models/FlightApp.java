package models;

import java.util.ArrayList;
import java.util.List;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.persistence.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

import java.text.DateFormat;
import java.util.Locale;

import javax.persistence.*;

public class FlightApp {
	
	
	
	
	

	
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
            
            System.out.println("Question A:");
            List<Flight> flights = em.createQuery("from Flight f where f.origin.country = 'USA' and f.destination.country != 'USA' and f.airplane.capacity > 500", Flight.class).getResultList();
            System.out.printf("%-9s%-31s%-31s\n", "Flight:", "Departs:", "Arrives:");
            for (Flight flight : flights) {
                System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
                        flight.getFlightnr(), flight.getOrigin().getCity(),
                        flight.getDepartureDate(), flight.getDepartureTime(),
                        flight.getDestination().getCity(),
                        flight.getArrivalDate(), flight.getArrivalTime());
            }
            System.out.println();

            em.getTransaction().commit();
            em.close();

            em = emf.createEntityManager();
            em.getTransaction().begin();

            // b) TODO: All airlines that use A380 airplanes
            System.out.println("Question B:");
            List<Airline> airlines = em.createQuery("select distinct a from Airline a join a.flights flight where flight.airplane.model = 'A380'", Airline.class).getResultList();
            System.out.println("Airlines that use A380s:");
            for (Airline airline : airlines) {
                System.out.println(airline.getName());
            }
            System.out.println();

            em.getTransaction().commit();
            em.close();

            em = emf.createEntityManager();
            em.getTransaction().begin();

            // c) TODO: Flights using 747 planes that don't belong to Star Alliance
            System.out.println("Question C:");
            flights = em.createQuery("select f from Flight f join f.airline a where f.airplane.model = '747' and a.name != 'Star Alliance'", Flight.class).getResultList();
            System.out.printf("%-9s%-31s%-31s\n", "Flight:", "Departs:",
                    "Arrives:");
            for (Flight flight : flights) {
                System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
                        flight.getFlightnr(), flight.getOrigin().getCity(),
                        flight.getDepartureDate(), flight.getDepartureTime(),
                        flight.getDestination().getCity(),
                        flight.getArrivalDate(), flight.getArrivalTime());
            }
            System.out.println();

            em.getTransaction().commit();
            em.close();

            em = emf.createEntityManager();
            em.getTransaction().begin();

            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
                    Locale.US);
            DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT,
                    Locale.US);

            // d) TODO: All flights leaving before 12pm on 08/07/2009
            System.out.println("Question D:");
            TypedQuery<Flight> query = em.createQuery("from Flight f where f.departureDate = :date and f.departureTime <= :time", Flight.class);
            query.setParameter("date", df.parse("08/07/2009"), TemporalType.DATE);
            query.setParameter("time", tf.parse("12:00 PM"), TemporalType.TIME);
            flights = query.getResultList();
            System.out.printf("%-9s%-31s%-31s\n", "Flight:", "Departs:",
                    "Arrives:");
            for (Flight flight : flights) {
                System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
                        flight.getFlightnr(), flight.getOrigin().getCity(),
                        flight.getDepartureDate(), flight.getDepartureTime(),
                        flight.getDestination().getCity(),
                        flight.getArrivalDate(), flight.getArrivalTime());
            }
            System.out.println();
            
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


	
	
	
	
	
	

	private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");
        		
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // a) TODO: Flights leaving USA capacity > 500
        System.out.println("Question A:");
        List<Flight> flights = em.createQuery("from Flight f where f.origin.country = 'USA' and f.destination.country != 'USA' and f.airplane.capacity > 500", Flight.class).getResultList();
        System.out.printf("%-9s%-31s%-31s\n", "Flight:", "Departs:", "Arrives:");
        for (Flight flight : flights) {
            System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
                    flight.getFlightnr(), flight.getOrigin().getCity(),
                    flight.getDepartureDate(), flight.getDepartureTime(),
                    flight.getDestination().getCity(),
                    flight.getArrivalDate(), flight.getArrivalTime());
        }
        System.out.println();

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        // b) TODO: All airlines that use A380 airplanes
        System.out.println("Question B:");
        List<Airline> airlines = em.createQuery("select distinct a from Airline a join a.flights flight where flight.airplane.model = 'A380'", Airline.class).getResultList();
        System.out.println("Airlines that use A380s:");
        for (Airline airline : airlines) {
            System.out.println(airline.getName());
        }
        System.out.println();

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        // c) TODO: Flights using 747 planes that don't belong to Star Alliance
        System.out.println("Question C:");
        flights = em.createQuery("select f from Flight f join f.airline a where f.airplane.model = '747' and a.name != 'Star Alliance'", Flight.class).getResultList();
        System.out.printf("%-9s%-31s%-31s\n", "Flight:", "Departs:",
                "Arrives:");
        for (Flight flight : flights) {
            System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
                    flight.getFlightnr(), flight.getOrigin().getCity(),
                    flight.getDepartureDate(), flight.getDepartureTime(),
                    flight.getDestination().getCity(),
                    flight.getArrivalDate(), flight.getArrivalTime());
        }
        System.out.println();

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
                Locale.US);
        DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT,
                Locale.US);

        // d) TODO: All flights leaving before 12pm on 08/07/2009
        System.out.println("Question D:");
        TypedQuery<Flight> query = em.createQuery("from Flight f where f.departureDate = :date and f.departureTime <= :time", Flight.class);
        query.setParameter("date", df.parse("08/07/2009"), TemporalType.DATE);
        query.setParameter("time", tf.parse("12:00 PM"), TemporalType.TIME);
        flights = query.getResultList();
        System.out.printf("%-9s%-31s%-31s\n", "Flight:", "Departs:",
                "Arrives:");
        for (Flight flight : flights) {
            System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
                    flight.getFlightnr(), flight.getOrigin().getCity(),
                    flight.getDepartureDate(), flight.getDepartureTime(),
                    flight.getDestination().getCity(),
                    flight.getArrivalDate(), flight.getArrivalTime());
        }
        System.out.println();
        em.getTransaction().commit();
        em.close();
    }
}