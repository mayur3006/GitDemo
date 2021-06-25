package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Account;

public class CrudTest {
	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	private static void update(){
		Session session = null;
		Transaction transaction = null;
		
		try{
			
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Account account = session.get(Account.class, 1);
			
			account.setBalance(6000);
			
			//session.update(account);
			
			transaction.commit();
			
			//session.flush();
			
		}finally{
			if(session != null) session.close();
		}
		
	}
	private static void updateWithDetached(){
		
		Session session = null;
		Transaction transaction = null;
		
		try{
			
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Account account = session.get(Account.class, 2);
			
			//persistent state
			
			System.out.println("account exists in session: " + session.contains(account));
			
			session.evict(account);			//remove object from session persistence context
			
			
			
			//account is now in detached state
			
			System.out.println("account exists in session: " + session.contains(account));
			
			
			account.setBalance(5000);
			account.setActive(false);
			
			
			session.update(account);			//transiting state from detached  to persistent state
			
			System.out.println("account exists in session: " + session.contains(account));
			transaction.commit();
			
		}finally{
			if(session != null) session.close();
		}
		
	}
	private static void updatewithTransient(){
		Session session = null;
		Transaction transaction = null;
		
		try{
			
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Account account = new Account();
			
			account.setBalance(6000);
			account.setName("Mohan");
			
			session.update(account);
			
			transaction.commit();
			
			//session.flush();
			
		}finally{
			if(session != null) session.close();
		}
		
	}
	private static void saveOrUpdateTransient(){
		Session session = null;
		Transaction transaction = null;
		
		try{
			
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Account account = new Account();
			
			account.setBalance(6000);
			account.setName("Mohan");
			
			session.saveOrUpdate(account);
			
			transaction.commit();
			
			//session.flush();
			
		}finally{
			if(session != null) session.close();
		}
		
	}
	private static void mergeWithDetached(){
		
		Session session = null;
		Transaction transaction = null;
		
		try{
			
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Account account = session.get(Account.class, 3);
			
			//persistent state
			
			System.out.println("account exists in session: " + session.contains(account));
			
			session.evict(account);			//remove object from session persistence context
			
			
			
			//account is now in detached state
			
			System.out.println("account exists in session: " + session.contains(account));
			
			
			account.setBalance(8000);
			account.setActive(true);
			
			
			session.merge(account);			//transiting state from detached  to persistent state
			
			System.out.println("account exists in session: " + session.contains(account));
			transaction.commit();
			
		}finally{
			if(session != null) session.close();
		}
		
	}
	private static void mergewithTransient(){
		Session session = null;
		Transaction transaction = null;
		
		try{
			
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Account account = new Account();
			
			account.setBalance(1050);
			account.setName("David");
			
			session.merge(account);
			
			transaction.commit();
			
			//session.flush();
			
		}finally{
			if(session != null) session.close();
		}
		
	}
	private static void removeObject(){
		
		Session session = null;
		Transaction transaction = null;
		
		try{
			
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			Account account = session.get(Account.class, 6);
			
			//persistent state
			
			//session.remove(account);
			session.delete(account);
			
			
			transaction.commit();
			
		}finally{
			if(session != null) session.close();
		}
		
	}
	public static void main(String[] args) {
		//update();
		
		//updateWithDetached();
		//updatewithTransient();
		//saveOrUpdateTransient();
		//mergeWithDetached();
		//mergewithTransient();
		removeObject();
		
		sessionFactory.close();
	}
}
