package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Account;

public class jpaTest {
	private static EntityManagerFactory entityManagerFactory = 
			Persistence.createEntityManagerFactory("jpaTest");


	private static void saveAccount(Account account){

		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try{
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();

			entityManager.persist(account);

			entityTransaction.commit();

			System.out.println("JPA Account object inserted successfully");

		}finally{

				if(entityManager != null) entityManager.close();

		}
	}


	public static void main(String[] args) {

		Account account = new Account();
		account.setId(3);
		account.setName("Ram");
		account.setBalance(1050);

		saveAccount(account);

	}
}
