package org.cmad.blog.api;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
@Embeddable
public class PersonalInfo {
	@Id
	private String name;
    private String email;
    private String pwd;
	private String phoneNum;
	//private Date DOB;
	private Long registeredTime;
	//@OneToOne
	@OneToOne(cascade = CascadeType.ALL)
	UserPosts post;

	public PersonalInfo() {
		super();
		this.registeredTime = System.currentTimeMillis();
	}
	public PersonalInfo(String name, String phoneNum) {
		super();
		this.name = name;
		this.phoneNum = phoneNum;
		//this.post = post;
		this.registeredTime = System.currentTimeMillis();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
//	
//	public Post getPost() {
//		return post;
//	}
//	public void setPost(Post post) {
//		this.post = post;
//	}
	
}
