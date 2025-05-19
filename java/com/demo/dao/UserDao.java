package com.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	// Basic methods provided by JpaRepository:
    // save(), saveAll(), findById(), findAll(), findAllById(), deleteById(), delete(), deleteAll(), count(), existsById()
    // Custom method
    Optional<User> findByEmailAndPassword(String email, String password);
}
