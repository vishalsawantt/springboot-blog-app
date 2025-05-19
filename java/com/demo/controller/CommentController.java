package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Comment;
import com.demo.service.CommentService;
import com.demo.service.PostService;
import com.demo.service.UserService;

@RestController
@RequestMapping("api/comment")
public class CommentController {
	@Autowired
	private CommentService cs;
	@Autowired
	private UserService us;
	@Autowired
	private PostService ps;

	@PostMapping("/add")
	public ResponseEntity<String> addComment(
			@RequestParam String comment,
			@RequestParam int postId,
			@RequestParam int userId) {

		cs.addComment(comment, postId, userId);
		return ResponseEntity.ok("Comment added successfully");
	}

	@PutMapping("/update/{commentId}")
	public ResponseEntity<String> updateComment(@PathVariable int commentId, @RequestParam String comment) {
	    cs.updateComment(commentId, comment); 
	    return ResponseEntity.ok("Updated");
	}

	// Delete a comment
	@DeleteMapping("/delete/{commentId}")
	public void deleteComment(@PathVariable int commentId) {
		cs.deleteComment(commentId);
	}

	// Get all comments by postId
	@GetMapping("/comments/{postId}")
	public List<Comment> getAllCommentsByPostId(@PathVariable int postId) {
	    return cs.getCommentsByPostId(postId);
	}

	@GetMapping("/getAll")
	public List<Comment> allComment(Comment comment) {
		return cs.findAll();
	}
	
	@GetMapping("/by-post/{postId}")
	public Page<Comment> getCommentsByPostPaginated(
	        @PathVariable int postId,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size) {

	    Pageable pageable = PageRequest.of(page, size, Sort.by("commentId").descending());
	    return cs.getCommentsByPost(postId, pageable);
	}

}
