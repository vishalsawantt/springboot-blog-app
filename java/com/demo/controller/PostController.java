package com.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.model.Post;
import com.demo.model.User;
import com.demo.service.PostService;
import com.demo.service.UserService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService ps;
    
    @Autowired
    private UserService us;

    @PostMapping("/save")
    public String savePost(@RequestParam("title") String title,
                           @RequestParam("content") String content,
                           @RequestParam("userId") int userId,
                           @RequestParam("image") MultipartFile imageFile) throws IOException {

        // Delegate the post saving logic to the service
        ps.save(title, content, userId, imageFile);

        return "Post saved successfully";  // Return a success message
    }

    
    @GetMapping("/postById/{postId}")   //by post table id it is post_id
    public Post getPostById(@PathVariable int id) {
        return ps.findById(id);
    }

    
    @GetMapping("/user/{id}")        //by user id which store in post table in user_id table
    public List<Post> getAllPostsByUser(@PathVariable int id) {
        return ps.findAllByUserId(id);
    }
    
    @GetMapping("/all")
    public List<Post> getAllPost(){
    	return ps.findAll();
    }
    
    @PutMapping("/update")
    public Post updatePost(@RequestParam int postId, @RequestBody Post updatedPost) {
        return ps.updatePost(postId, updatedPost);
    }
    
    @DeleteMapping("/delete/{id}")
    public String deletePost(@PathVariable int id) {
        ps.deletePostById(id);  
        return "Post deleted successfully";
    }
    
    @GetMapping("/images/{postId}")
    public ResponseEntity<byte[]> getImage(@PathVariable int postId) {  // Use int if your postId is int
        Post post = ps.findById(postId); // Use your existing service method
        if (post != null && post.getImage() != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(post.getImage());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/others/{loggedInUserId}")
    public List<Post> getPostsOfOtherUsers(@PathVariable int loggedInUserId) {
        return ps.findPostsExcludingUser(loggedInUserId);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(@RequestParam("keyword") String keyword) {
        List<Post> result = ps.searchPosts(keyword);
        return ResponseEntity.ok(result);
    }
}
