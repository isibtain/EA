package model;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class AppBook {

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
            
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);

            // Create new instance of Book and set values in it
            Book book1 = new Book("A Good Book", "345","Sibtain",33.5, df.parse("05/10/1997") );
            // save the book
            session.persist(book1);
            // Create new instance of Book and set values in it
            Book book2 = new Book("Cook Book", "678","Khan",53.5, df.parse("05/10/1999") );
            // save the book
            session.persist(book2);
            
            Book book3 = new Book("Another Book", "644","Ali",76.5, df.parse("05/10/2006") );
            // save the book
            session.persist(book3);
            
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (session != null) {
                session.close();
            }
        }

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // retrieve all books
            @SuppressWarnings("unchecked")
            List<Book> bookList = session.createQuery("from Book").list();
            for (Book book : bookList) {
                System.out.println("Title= " + book.getTitle() + ", Author= " +
                		  book.getAuthor() + ", Year= " + book.getPublish_date() + ", ISBN= "
                		  +book.getISBN() + ", Price= " +book.getPrice() );
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
        
        
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Update one of the Books
            Book dbBook = (Book) session.get(Book.class, 1L);
            System.out.println(dbBook.getId() + " - " + dbBook.getTitle());
            dbBook.setTitle("Not Good Book"); dbBook.setPrice(99.9);
            //Delete another book
            Book dbBook2 = (Book) session.get(Book.class, 2L);
            System.out.println(dbBook2.getId() + " - " + dbBook2.getTitle());
            session.delete(dbBook2);
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

            // retrieve all books again
            @SuppressWarnings("unchecked")
            List<Book> bookList = session.createQuery("from Book").list();
            for (Book book : bookList) {
                System.out.println("Title= " + book.getTitle() + ", Author= " +
                		  book.getAuthor() + ", Year= " + book.getPublish_date() + ", ISBN= "
                		  +book.getISBN() + ", Price= " +book.getPrice() );
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

        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
        System.exit(0);
    }
}
