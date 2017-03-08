package org.cmad.blog.data;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.cmad.blog.api.Comment;
import org.cmad.blog.api.UserPosts;



import org.cmad.blog.api.User;

public class MyBlogDAO/* implements DAO*/ {

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("blog");

	public User createUser(User user) {

		EntityManager em = factory.createEntityManager();
		User usr = em.find(User.class, user.getEmail());
		if (usr == null) {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			em.close();
			return user;
		}
		em.close();
		return null;

	}

	public User getUser(User user) {
		EntityManager em = factory.createEntityManager();
		User usr = em.find(User.class, user.getEmail());
		System.out.println("BlogDAO.getUser() Got usr:"+usr+" from "+user.getEmail());
		if (usr != null) {
			em.close();
			return usr;
		}
		em.close();
		return null;

	}
	public User updateUser(User user) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();
		return user;
	}

/*	public void createPost(UserPosts post) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(post);
		em.getTransaction().commit();
		em.close();
	}*/

	public UserPosts readPost(int postId) {
		EntityManager em = factory.createEntityManager();
		UserPosts post = em.find(UserPosts.class, postId);
		
		em.close();
		return post;
	}

	public int postComment(Comment comment) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(comment);
		em.getTransaction().commit();
		em.close();
		return 0;//omment.getId();

	}

}
