package com.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.demo.controller.UserController;
import com.demo.dao.UserDao;
import com.demo.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao ud;

	@Override
	public User save(User u) {
		return ud.save(u);
	}

	@Override
	public Optional<User> findByEmailAndPassword(String email, String password) {
		return ud.findByEmailAndPassword(email, password);
	}

	@Override
	public List<User> getAllUsers() {
		return ud.findAll();
	}

	@Override
	public User getUserById(int id) {
		return ud.findById(id).orElse(null);
	}

	@Override
	public User updateUser(int id, User updatedUser) {
		User u = getUserById(id);
		u.setName(updatedUser.getName());
		u.setEmail(updatedUser.getEmail());
		u.setPassword(updatedUser.getPassword());
		return ud.save(u);
	}

	@Override
	public void deleteUserById(int id) {
		ud.deleteById(id);
	}
}
