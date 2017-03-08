package org.cmad.blog.api;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


@Entity("posts")
public class UserPosts {
	@Id
	private ObjectId id;
	private String title;
	private String body;
	//private User userauthor;
	private Date createdTime;
	private int likes;
	private String author;
	private String imageURL;
	private List<Comment> comments;

	public UserPosts() {
	}

	public UserPosts(String title, String body,int likes,String author, String imageURL) {
		super();
		this.title = title;
		this.body = body;
		this.createdTime = new Date();
		this.likes = likes;
		this.author = author;
		this.imageURL = imageURL;
	}
	
	
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Post [" +getTitle()+" "+getLikes()+" "+getAuthor()+"] ";
	}

}
