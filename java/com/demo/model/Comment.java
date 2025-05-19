package com.demo.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentId;
	private String comment;
	
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@ManyToOne
    private User user;
	
	@ManyToOne
	private Post post;

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", comment=" + comment + ", createdAt=" + createdAt + ", user="
				+ user + ", post=" + post + "]";
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
  
	public User getUser() { 
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
