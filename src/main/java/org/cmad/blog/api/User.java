package org.cmad.blog.api;

import java.util.Collection;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

import javax.xml.bind.annotation.XmlRootElement;

@Entity("users")
@Indexes(
		@Index(value = "email", fields = @Field("email"))
		)
@XmlRootElement
public class User {

	@Id
	private ObjectId userId;
	private String email;
	//private Collection <UserPosts>  userposts = new ArrayList<UserPosts>();
	private String password;
	private String name;

	public User() {
		super();
	}

	public User(String email, String password,String name){
		super();
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public User(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Collection<UserPosts> getUserposts() {
		return null;
	}

	public void setUserposts(Collection<UserPosts> userposts) {
		//this.userposts = userposts;
	}
	

	@Override
	public String toString() {
		return  "User ["+getEmail()+" "+getName()+"]";
	}

	public ObjectId getUserId() {
		return userId;
	}

	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}

}
