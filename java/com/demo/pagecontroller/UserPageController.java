package com.demo.pagecontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.model.Post;
import com.demo.model.User;
import com.demo.service.LikeService;
import com.demo.service.PostService;
import com.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserPageController {

	@Autowired
	private UserService us;

	@Autowired
	private PostService ps;

	@Autowired
	private LikeService likeService;

	// Show registration page
	@GetMapping("/uiregister")
	public String showRegisterPage(Model model) {
		model.addAttribute("user", new User());
		return "register"; 
	}

	@GetMapping("/uilogin")
	public String showLoginPage() {
		return "login"; 
	}

	@GetMapping("/uidashboard")
	public String showDashboard(@RequestParam("userId") int userId, Model model) {
		User user = us.getUserById(userId);
		model.addAttribute("user", user);

		// Fetch only posts of OTHER users
		List<Post> posts = ps.findPostsExcludingUser(userId);
		model.addAttribute("posts", posts);

		// Create a Map<PostId, LikeCount>
		Map<Integer, Integer> likeCounts = new HashMap<>();
		Map<Integer, Boolean> userLikes = new HashMap<>();

		for (Post post : posts) {
			int pid = post.getPostId();
			likeCounts.put(pid, likeService.getLikeCount(pid));
			userLikes.put(pid, likeService.isLiked(pid, userId));
		}

		model.addAttribute("likeCounts", likeCounts);
		model.addAttribute("userLikes", userLikes);

		return "dashboard"; // dashboard.html
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/uilogin";
	}

}
