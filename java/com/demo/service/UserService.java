package com.demo.service;

import java.util.List;
import java.util.Optional;

import com.demo.model.User;

public interface UserService {
	// always declare all the methods you want (in-built + custom) because
	// Controller talks to Service, not directly to DAO.
	User save(User u);// save()

	Optional<User> findByEmailAndPassword(String email, String password);// custome method

	List<User> getAllUsers();// findAll()

	User getUserById(int id);// findById

	User updateUser(int id, User updatedUser);// custome method

	void deleteUserById(int id);// deleteById()
}
