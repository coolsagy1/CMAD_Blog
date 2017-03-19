package org.cmad.blog.data;

import java.util.List;

import org.cmad.blog.api.Comment;
import org.cmad.blog.api.UserPosts;
import org.cmad.blog.api.User;

public interface DAO {

	User createUser(User user);

	User getUser(User user);
	
	User updateUser(User user);

	int createPost(UserPosts post);

	UserPosts readPost(int postId);

	int postComment(Comment comment);

	List<User> getUsers();

	List<UserPosts> getPosts();
	
	List<UserPosts> getPosts(String str);

	void updatePost(UserPosts post);

}
