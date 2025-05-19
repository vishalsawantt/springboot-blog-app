package com.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.demo.model.Post;

public interface PostService {
	Post save(String title, String content, int userId, MultipartFile imageFile);

	List<Post> findAllByUserId(int id);// find all post by user id->for MYPOST button

	List<Post> findAll();// find all post for showing all user timeline

	Post updatePost(int id, Post updatedPost);// update the post by post id

	void deletePostById(int id);// delete post

	Post findById(int postId);

	List<Post> findPostsExcludingUser(int userId);

	List<Post> searchPosts(String keyword);

}
