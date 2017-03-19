package com.cmad.blog.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.cmad.blog.api.Comment;
import org.cmad.blog.api.User;
import org.cmad.blog.api.UserPosts;
import org.cmad.blog.data.DAO;



public class BlogDAOTest implements DAO {

    private static BlogDAOTest dao = null;

    private static final Object LOCK = new Object();
    
    private List<User> users = new ArrayList<User>();
    private List<UserPosts> userPosts = new ArrayList<UserPosts>();
    private List<UserPosts> comments = new ArrayList<UserPosts>();

    private BlogDAOTest() {
    }

    public static BlogDAOTest getInstance() {
        synchronized (LOCK) {
            if (dao == null) {
                dao = new BlogDAOTest();
            }
        }
        return dao;
    }

    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }
    
    public User getUser(User usr) {
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
            User user = (User) iterator.next();
            if (user.equals(usr)) {
                return user;
            }
        }
        return null;
    }

    public List<User> listUsers() {
        return users;
    }

    public void addpost(UserPosts userPost) {
        if (!userPosts.contains(userPost)) {
            userPosts.add(userPost);
        }
    }

    public List<UserPosts> listQuestions() {
        return userPosts;
    }

    public List<UserPosts> findQuestions(String searchPattern) {
        List<UserPosts> result = new ArrayList<UserPosts>();
        for (Iterator<UserPosts> iterator = userPosts.iterator(); iterator.hasNext();) {
        	UserPosts userPost = (UserPosts) iterator.next();
            String title = userPost.getTitle();
            String description = userPost.getBody();
            
            if (title.contains(searchPattern)) {
                result.add(userPost);
            } else if (description.contains(searchPattern)) {
                result.add(userPost);
            }
        }
        return result;
    }
    
   
    public void resetTest() {
        users.clear();
        userPosts.clear();
    }

	@Override
	public User createUser(User user) {
		 if (!users.contains(user)) {
	            users.add(user);
	            return user;
	        }
		 return null;
	}

	

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createPost(UserPosts userPost) {
		 if (!userPosts.contains(userPost)) {
	            userPosts.add(userPost);
	            return 0;
	        }
		 return -1;
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
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserPosts> getPosts() {
		return this.userPosts;
	}

	@Override
	public void updatePost(UserPosts post) {
		// TODO Auto-generated method stub
		
	}

	public List<UserPosts> getPosts(String str) {
		// TODO Auto-generated method stub
		return null;
	}
}
