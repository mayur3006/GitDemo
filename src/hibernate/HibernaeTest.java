package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Account;

public class HibernaeTest {
	
	//reads all configuration details from hibernate.cfg.xml
	private static SessionFactory sessionFactory = new Configuration()
																.configure().buildSessionFactory();
	
	/*static{
		
		Configuration configuration = new Configuration();
		configuration = configuration.configure();
		SessionFactory buildSessionFactory = configuration.buildSessionFactory();
		
		
	}*/
	
	private static void saveAccount(Account account){
		
		Session session = null;
		Transaction transaction = null;
		
		try{
			
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			//transaction.begin();
			
			//session.persist(account);
			//Serializable save= session.save(account);
			
			session.save(account);
			
			System.out.println("After save method is called and befor commit");
			
			transaction.commit();
			
			System.out.println("Hibernate Account object created....");
			
		}finally{
			
			if(session != null) session.close();
			
		}
		
		
		
	}
	
	private static void getAccount() {
		/*
		Session session = null;
		
		try{
			
			session = sessionFactory.openSession();
		}finally{
			
			if(session != null) session.close();
			
		}*/
		try(Session session = sessionFactory.openSession()){
			Account account = session.get(Account.class, 3);
			
			Account account1 = session.get(Account.class, 3);	
			
			if(account == account1){
				System.out.println("Identical");
			}else{
				System.out.println("Not Identical");
			}
			System.out.println("get method Account class: " + account.getClass());
			System.out.println("After session get method is called");
			System.out.println(account.getName());
		}
		
	}
	private static void loadAccount(){
		
		try(Session session = sessionFactory.openSession()){
			
			Account account = session.load(Account.class, 4);	//This will work only with primary key column value
										//SQL Select query is fired
			
			System.out.println("get method Account class: " + account.getClass());
			
			System.out.println("After session get method is called");
			
			System.out.println(account.getName());
			
			
		}
		
	}
	public static void main(String[] args) {
		
		/*Account account = new Account();
		//account.setId(4);
		account.setName("Rohan");
		account.setBalance(1050);
		account.setActive(true);
		saveAccount(account);
		
		Account account1 = new Account();
		//account.setId(4);
		account1.setName("Ram");
		account1.setBalance(1050);
		account1.setActive(true);
		saveAccount(account1);
		
		Account account2 = new Account();
		//account.setId(4);
		account2.setName("Sham");
		account2.setBalance(1050);
		account2.setActive(true);
		saveAccount(account2);
		
		Account account3 = new Account();
		//account.setId(4);
		account3.setName("Karan");
		account3.setBalance(1050);
		account3.setActive(true);
		saveAccount(account3);*/
		
		//getAccount();
		loadAccount();
		sessionFactory.close();
	}
	
}
