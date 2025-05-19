package com.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PostLike {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int likeId;
	  
	@ManyToOne
	private User user;

	@ManyToOne
	private Post post;
 
	@Override
	public String toString() {
		return "Like [likeId=" + likeId + ", user=" + user + ", post=" + post + "]";
	}
	
    public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
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
