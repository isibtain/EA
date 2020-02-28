package models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class AppLaptop {

		
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
	            
	            
	            
	            Set<Laptop> lappies = new HashSet();
	            
	            Employee emp1 = new Employee("John","Smith",lappies);
	            Employee emp2 = new Employee("Steve","Jobs",lappies);
	            
	            lappies.add(new Laptop("HP","Fast",emp1));
	            lappies.add(new Laptop("Dell", "Slow", emp1));
	            lappies.add(new Laptop("Apple", "Small", emp2));
	            lappies.add(new Laptop("Acer", "Cute", emp1));
	            lappies.add(new Laptop("Google", "Andr", emp2));
	            lappies.add(new Laptop("Compaq", "Micro", emp1));
	            
	            emp1.setLaptops(lappies);
	            emp2.setLaptops(lappies);
	            
	           
	            session.persist(emp1);
	            session.persist(emp2);

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
	        
	        try {
	            session = sessionFactory.openSession();
	            tx = session.beginTransaction();

	            // retieve all employees
	            @SuppressWarnings("unchecked")
	            List<Employee> empList = session.createQuery("from Employee").list();
	            for (Employee emp : empList) {
	                System.out.println("Firstname= " + emp.getFirstname() + ", Lastname= "
	                        + emp.getLastname() + ", laptops= " + emp.getLaptops());
	            }
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
