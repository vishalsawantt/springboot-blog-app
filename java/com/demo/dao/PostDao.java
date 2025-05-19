package com.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.Post;

@Repository
public interface PostDao extends JpaRepository<Post, Integer>{
	List<Post> findAllByUserId(int id);
	List<Post> findAll();
    List<Post> findByUser_IdNot(int userId);
    
    // Search posts by title or content
    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Post> searchPostsByKeyword(@Param("keyword") String keyword);
}
