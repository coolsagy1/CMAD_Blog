package org.cmad.blog.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.cmad.blog.Blog;
import org.cmad.blog.api.User;
import org.cmad.blog.api.UserPosts;
import org.cmad.blog.biz.SimpleBlog;
import org.cmad.blog.data.BlogDAO;

@Path("/user")
public class BlogController {
	Blog blog;
	public BlogController() {
		blog = new SimpleBlog();
		BlogDAO blogDao = new BlogDAO();
		blog.setDAO(blogDao);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/signup")
	public Response add(User user) {
		User us = blog.addUser(user);
		return Response.ok().entity(us).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/signin")
	public Response signIn(@Context HttpServletRequest request) {
		/*User usr = blog.getUser(user);
		System.out.println("BlogController.signIn() usr:"+usr);
		return Response.ok().entity(usr).build();*/
		String userName = (String) request.getSession().getAttribute("username");
		System.out.println("BlogController.signIn() Login for "+userName);
		User user = blog.getUser(new User(userName));
		System.out.println("BlogController.signIn() usr:"+user);
		if(user!=null)
		{	
			System.out.println("BlogController.login() login successful with userName:"+userName);
			return Response.ok().entity(user).build();
		}
		else
		{	
			System.out.println("BlogController.signIn() login failed!");
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}

	/**
	 * Method for REST API Verification
	 * @param user
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(User user) {
		/*User usr = blog.getUser(user);
		System.out.println("BlogController.signIn() usr:"+usr);
		return Response.ok().entity(usr).build();*/
		System.out.println("BlogController.signIn() user:"+user);
		return Response.ok().build();

	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addPost")
	public Response addBlogPost(UserPosts post) {
		blog.addPost(post);
		return Response.ok().build();
	}

	/*
	 * Search Method
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/search")
	public Response getPosts(@QueryParam("searchString") String searchString) {
		System.out.println("BlogController.getPosts()------>>>>ATEESH searchString :"+searchString);
		List<UserPosts> posts = null;
		try{
			posts = blog.getPosts(searchString);
		}
		catch (Exception e) {
			return Response.serverError().entity("Some Error Occurred! Please Try Again").build();
		}
		if (posts == null || posts.isEmpty()) {
			return Response.serverError().entity("Nothing found").build();
		}
		return Response.ok().entity(posts).build();
	}
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/posts")
	public Response addPost(UserPosts post) {
		System.out.println("BlogController.addPost() post:"+post);
		blog.addPost(post);
		return Response.ok().build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/posts")
	public Response updatePost(UserPosts post) {
		System.out.println("BlogController.updatePost() post:"+post);
		blog.updatePost(post);
		return Response.ok().build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getUsers")
	public Response getUsers() {
		List<User> userList = blog.getUsers();
		return Response.ok().entity(userList).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/posts")
	public Response getPosts() {
		List<UserPosts> postList = blog.getPosts();
		return Response.ok().entity(postList).build();
	}

}
