package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Students;

public class studentJpa {
	private static EntityManagerFactory entityManagerFactory = 
			Persistence.createEntityManagerFactory("studentJpa");
	
	private static void AddStudent(Students std){

		EntityManager entityManager = null;
		EntityTransaction entityTransaction = null;

		try{
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();

			entityTransaction.begin();

			entityManager.persist(std);

			entityTransaction.commit();

			System.out.println("JPA student object inserted successfully");

		}finally{

				if(entityManager != null) entityManager.close();

		}
	}


	public static void main(String[] args) {

		Students s1 = new Students();
		s1.setId(3);
		s1.setName("Ram");
		s1.setCoursename("Daca");
		s1.setFees(50000);
		s1.setPercentage(88.45);
		AddStudent(s1);

	}
}
