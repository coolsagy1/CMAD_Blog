package org.cmad.blog.api;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public class Comment {
	
	@Id
	private ObjectId id;
	private String comment;
	private Date createdTime;
	private String author;

	public Comment() {
		super();
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Date getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public Comment(String comment, Date createdTime, String author) {
		super();
		this.comment = comment;
		this.createdTime = createdTime;
		this.author = author;
	}


	public ObjectId getId() {
		return id;
	}


	public void setId(ObjectId id) {
		this.id = id;
	}


}
