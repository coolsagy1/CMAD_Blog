package org.cmad.blog.biz;

import java.util.List;

import org.cmad.blog.Blog;
import org.cmad.blog.BlogException;
import org.cmad.blog.api.Comment;
import org.cmad.blog.api.UserPosts;
import org.cmad.blog.PostNotFoundException;
import org.cmad.blog.api.Topic;
import org.cmad.blog.api.User;
import org.cmad.blog.data.BlogDAO;
import org.cmad.blog.data.DAO;
import org.cmad.blog.data.TestDao;

public class SimpleBlog implements Blog {

	DAO blogDao ;

	TestDao dao;

	public SimpleBlog() {
		blogDao = new BlogDAO();
		//dao = new TestDao();

	}

	public User addUser(User user) throws BlogException {
		if(user==null || user.getEmail().trim().length() == 0)
			throw new BlogException();
		User result = blogDao.createUser(user);
		return  result;
	}

	public List<User> getUsers() {
		List<User> userList = blogDao.getUsers();
		System.out.println("SimpleBlog.getUsers() result:"+userList);
		return userList;
	}

	public List<UserPosts> getPosts() {
		List<UserPosts> postList = blogDao.getPosts();
		System.out.println("SimpleBlog.getPosts() result:"+postList);
		return postList;
	}


	public User getUser(User user) {
		if(user==null ||user.getEmail().trim().length()==0)
			throw new BlogException();
		User result = blogDao.getUser(user);
		System.out.println("SimpleBlog.getUser() result:"+result);
		return result;
	}

	public List<UserPosts> addPost(UserPosts post) throws BlogException {
		/*User user = getUser(post.getUser());
		     user.getUserposts().add(post);*/
		blogDao.createPost(post);
		// updateUser(user);
		return null;
	}

	public Comment addComment(Comment comment) throws BlogException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserPosts> getPosts(User user) throws BlogException {
		return null;
	}

	public List<UserPosts> getPosts(Topic topic) throws BlogException {
		// TODO Auto-generated method stub
		return null;
	}

	public UserPosts getPost(int postId) throws PostNotFoundException, BlogException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) throws BlogException {
		User usr = blogDao.updateUser(user);
		return usr;
	}

	@Override
	public void updatePost(UserPosts post) {
		blogDao.updatePost(post);
	}
}
