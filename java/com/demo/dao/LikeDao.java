package com.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.Post;
import com.demo.model.PostLike;
import com.demo.model.User;

@Repository
public interface LikeDao extends JpaRepository<PostLike, Integer>{
	int countByPost_PostId(int postId);
    boolean existsByUser_IdAndPost_PostId(int userId, int postId);
    void deleteByUser_IdAndPost_PostId(int userId, int postId);
}
