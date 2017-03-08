package org.cmad.blog.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.cmad.blog.api.User;



public class TestDao {
	//static EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("blog");
	
	
	public TestDao(){
		
	}
	public int createUser(User user) {
		
		System.out.println("UserActivityHandler.performLogin()0000000");
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("blog");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		return 0;
	}
}
