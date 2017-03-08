package com.cmad.blog.rs;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.cmad.blog.api.User;
import org.cmad.blog.api.UserPosts;
import org.cmad.blog.biz.SimpleBlog;
import org.cmad.blog.data.BlogDAO;
import org.junit.Assert;
import org.junit.Test;

import com.cmad.blog.data.BlogDAOTest;

import junit.framework.TestCase;

public class BlogTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testGetUserWithEmail() {
        BlogDAO dao = mock(BlogDAO.class);
        
        User user = new User();
        user.setName("ateesh");
        user.setEmail("ateesh@cisco.com");
        user.setPassword("123456");
      
        
        when(dao.getUser(user)).thenReturn(user);
        
        
        SimpleBlog blog = new SimpleBlog();
        blog.setDAO(dao);
        User u = blog.getUser(user);
        Assert.assertEquals("ateesh", u.getName());
    }
    
    @Test
    public void testAddUser() {

    	BlogDAOTest dao = BlogDAOTest.getInstance();
        User user = new User();
        user.setEmail("ateesh@cisco.com");
        user.setName("ateesh");
        user.setPassword("123456");
       

        SimpleBlog blog = new SimpleBlog();
        blog.setDAO(dao);
        blog.addUser(user);
        User u = blog.getUser(user);
        Assert.assertEquals("ateesh", u.getName());
        dao.resetTest();
    }
    
    @Test
    public void testAddPost() {
        BlogDAOTest dao = BlogDAOTest.getInstance();
        
        UserPosts userPost = new UserPosts();
        userPost.setTitle("I want to learn C++");
        userPost.setBody("Ok lets learn C++");
                
        SimpleBlog blog = new SimpleBlog();
        blog.setDAO(dao);
        
        System.out.println("BlogTest.testAddPost()"+blog.getPosts());
        blog.addPost(userPost);
        
        List<UserPosts> userPosts = blog.getPosts();
        
        assertEquals(true, userPosts.contains(userPost));
        dao.resetTest();
    }
    
    
    
    @Test
    public void testgetAllPosts() {
        BlogDAOTest dao = BlogDAOTest.getInstance();
        SimpleBlog blog = new SimpleBlog();
        blog.setDAO(dao);
        
        UserPosts userPosts = new UserPosts();
        userPosts.setTitle("I want to learn C++");
        userPosts.setBody("Ok lets learn C++");
        blog.addPost(userPosts);
        
        UserPosts userPost1 = new UserPosts();
        userPost1.setTitle("I want to learn java");
        userPost1.setBody("Ok lets learn java");
        blog.addPost(userPost1);

        
        List<UserPosts> allposts = blog.getPosts();
        assertEquals(2, allposts.size());
        dao.resetTest();
    }
    //TODO add comment code
    @Test
    public void testgetAllComments() {
        BlogDAOTest dao = BlogDAOTest.getInstance();
        SimpleBlog blog = new SimpleBlog();
        blog.setDAO(dao);
        
        UserPosts userPosts = new UserPosts();
        userPosts.setTitle("I want to learn C++");
        userPosts.setBody("Ok lets learn C++");
        blog.addPost(userPosts);
        
        UserPosts userPost1 = new UserPosts();
        userPost1.setTitle("I want to learn java");
        userPost1.setBody("Ok lets learn java");
        blog.addPost(userPost1);

        
        List<UserPosts> allposts = blog.getPosts();
        assertEquals(2, allposts.size());
        dao.resetTest();
    }
    //TODO add comment code
    @Test
    public void testaddComments() {
        BlogDAOTest dao = BlogDAOTest.getInstance();
        SimpleBlog blog = new SimpleBlog();
        blog.setDAO(dao);
        
        UserPosts userPosts = new UserPosts();
        userPosts.setTitle("I want to learn C++");
        userPosts.setBody("Ok lets learn C++");
        blog.addPost(userPosts);
        
        UserPosts userPost1 = new UserPosts();
        userPost1.setTitle("I want to learn java");
        userPost1.setBody("Ok lets learn java");
        blog.addPost(userPost1);

        
        List<UserPosts> allposts = blog.getPosts();
        assertEquals(2, allposts.size());
        dao.resetTest();
    }
}
