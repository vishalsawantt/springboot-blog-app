package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.User;
import com.demo.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService us;

	@PostMapping("/register")
	public User saveUser(@RequestBody User user) {
	    return us.save(user);  // Save and return saved user (with ID)
	}

	@PostMapping("/login")
	public Object loginUser(@RequestParam String email, @RequestParam String password) {
		User user = us.findByEmailAndPassword(email, password).orElse(null);
		return (user != null) ? user : "Incorrect email or password.";
	}

	@GetMapping("/allusers")
	public List<User> getAllUsers() {
		return us.getAllUsers();
	}

	@GetMapping("/getuser/{id}")
	public User getUserById(@PathVariable int id) {
		return us.getUserById(id);
	}

	@PutMapping("/updateuser/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User updatedUser) {
		return us.updateUser(id, updatedUser);
	}

	@DeleteMapping("/deleteuser/{id}")
	public String deleteUser(@PathVariable int id) {
		us.deleteUserById(id);
		return "User deleted successfully.";
	}
}
