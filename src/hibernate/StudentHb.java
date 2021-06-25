package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Students;

public class StudentHb {
	private static SessionFactory sessionFactory = new Configuration()
			.configure().buildSessionFactory();
	
	private static void AddStudent(Students std){
		
		Session session = null;
		Transaction transaction = null;
		
		try{
			
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			//transaction.begin();
			
			session.persist(std);
			
			transaction.commit();
			
			System.out.println("Hibernate Account object created....");
			
		}finally{
			
			if(session != null) session.close();
			
		}
	}
	
	
	public static void main(String[] args) {
		
		Students s2 = new Students();
		s2.setId(4);
		s2.setName("Rohan");
		s2.setCoursename("C++");
		s2.setFees(3000);
		s2.setPercentage(77.45);
		
		AddStudent(s2);
		
	}
}
