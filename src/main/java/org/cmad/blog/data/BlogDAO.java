package org.cmad.blog.data;

import java.util.List;

import org.cmad.blog.api.Comment;
import org.cmad.blog.api.User;
import org.cmad.blog.api.UserPosts;
import org.mongodb.morphia.Datastore;

public class BlogDAO implements DAO {

	private Datastore dStore;

	public BlogDAO() {
		dStore = MongoServiceFactory.getMongoDB();
		System.out.println("BlogDAO.BlogDAO() dStore:"+dStore);
	}

	public List<User> getUsers() {
		return dStore.createQuery(User.class).asList();
	}

	public List<UserPosts> getPosts() {
		return dStore.createQuery(UserPosts.class).asList();
	}

	public User createUser(User user) {
		dStore.save(user);
		return user;
	}

	public User getUser(User user) {
		List<User> users = dStore.createQuery(User.class).field("email")
				.equal(user.getEmail()).asList();
		return users.size() > 0 ? users.get(0) : null;
	}

	public int createPost(UserPosts post) {
		System.out.println("BlogDAO.createPost() post:"+post);
		dStore.save(post);
		return 0;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserPosts readPost(int postId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int postComment(Comment comment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updatePost(UserPosts post) {
		dStore.merge(post);		
	}
}
