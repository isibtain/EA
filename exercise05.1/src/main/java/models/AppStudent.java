package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class AppStudent {


	
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
            
            Product pr1 = new CD("Himesh");
            pr1.setDescription("A CD of Himesh Songs");
            pr1.setName("Himesh Songs");
            
            Product pr2 = new Book("A Good Book");
            pr2.setDescription("This is a good Book");
            pr2.setName("GOOD BOOK");
            
            Product pr3 = new DVD("Sotware");
            pr3.setDescription("This is a software DVD");
            pr3.setName("Windows Software");
            
            OrderLine ol1 = new OrderLine();
            OrderLine ol2 = new OrderLine();
            OrderLine ol3 = new OrderLine();
            OrderLine ol4 = new OrderLine();
            
            ol1.setProduct(pr1);
            ol1.setQuantity(15);
            
            ol2.setProduct(pr1);
            ol2.setQuantity(43);
            
            ol3.setProduct(pr2);
            ol3.setQuantity(55);
            
            ol4.setProduct(pr3);
            ol4.setQuantity(47);
            
            List<OrderLine> orderlines = new ArrayList();
            List<OrderLine> orderlines2 = new ArrayList();
            
            orderlines.add(ol1);
            orderlines.add(ol3);
            
            orderlines2.add(ol2);
            orderlines2.add(ol4);
            
            Order o1 = new Order(df.parse("05/10/1997"),  orderlines);
            Order o2 = new Order(df.parse("05/10/2007"),  orderlines2);
            Order o3 = new Order(df.parse("05/10/2017"),  orderlines2);
            Order o4 = new Order(df.parse("05/10/1998"),  orderlines);
            
           
            List<Order> orders1 = new ArrayList();
            List<Order> orders2 = new ArrayList();
            
            orders1.add(o1);
            orders1.add(o2);
            orders2.add(o3);
            orders2.add(o4);

            
            
            Customer c1 = new Customer();
            Customer c2 = new Customer();
            
            o1.setOwner(c1);
            o2.setOwner(c1);
            o3.setOwner(c2);
            o4.setOwner(c2);
            
            
            
            
            c1.setFirstname("Babar");
            c1.setLastname("Azam");
            c1.setOrders(orders1);
            
            c2.setFirstname("Shoaib");
            c2.setLastname("Akhtar");
            c2.setOrders(orders2);
            
            
            
            
            
            session.persist(c1);
            session.persist(c2);
            
            
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
