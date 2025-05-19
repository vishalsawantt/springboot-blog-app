package com.demo.pagecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.model.Post;
import com.demo.service.PostService;
import com.demo.service.UserService;

@Controller
public class PostPageController {

	@Autowired
	private PostService ps;

	@Autowired
	private UserService us;

	@GetMapping("/create-post")
	public String showCreatePostPage(@RequestParam("userId") int userId, Model model) {
		model.addAttribute("userId", userId);
		return "createpost";
	}

	//your PostPageController is calling the PostService, which calls the Impl class, and that class uses the DAO to fetch data from the database. This is the proper layered architecture.
	@GetMapping("/mypost")
	public String showMyPosts(@RequestParam("userId") int userId, Model model) {
	    List<Post> posts = ps.findAllByUserId(userId);
	    model.addAttribute("myPosts", posts);
	    model.addAttribute("userId", userId);
	    return "mypost"; 
	}
	
	@GetMapping("/updatepost/{postId}")
	public String showUpdatePostPage(@PathVariable("postId") int postId, @RequestParam("userId") int userId, Model model) {
	    Post post = ps.findById(postId); // Use your service method
	    model.addAttribute("post", post);
	    model.addAttribute("userId", userId);
	    return "updatepost"; // Thymeleaf will render updatepost.html
	}
}
