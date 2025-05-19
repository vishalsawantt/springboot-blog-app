package com.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.model.Comment;

public interface CommentService {
	void addComment(String commentText, int postId, int userId);

	List<Comment> getCommentsByPostId(int postId);

	Comment updateComment(int commentId, String updatedCommentText);

	void deleteComment(int commentId);

	List<Comment> findAll();

	Page<Comment> getCommentsByPost(int postId, Pageable pageable);

}
