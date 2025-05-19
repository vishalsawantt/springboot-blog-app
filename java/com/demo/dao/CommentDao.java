package com.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment, Integer>{
	List<Comment> findByPost_PostId(int postId);     //getCommentsByPostId
	List<Comment> findAll();
	Page<Comment> findByPost_PostId(int postId, Pageable pageable);
}
