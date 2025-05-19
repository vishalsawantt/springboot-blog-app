package com.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.demo.dao.PostDao;
import com.demo.model.Post;
import com.demo.model.User;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao pd;

	@Autowired
	private UserService us;

	@Override
	public Post save(String title, String content, int userId, MultipartFile imageFile) {
		// Create and populate the Post object
		User user = us.getUserById(userId); // Get the user

		Post post = new Post();
		post.setTitle(title);
		post.setContent(content);
		try {
			post.setImage(imageFile.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Set image bytes
		post.setUser(user); // Set the user for the post

		// Save the Post using the DAO
		return pd.save(post); 
	}

	@Override
	public Post findById(int postId) {
		return pd.findById(postId).orElse(null);
	}

	@Override
	public List<Post> findAllByUserId(int id) {
		return pd.findAllByUserId(id);
	}

	@Override
	public List<Post> findAll() {
		return pd.findAll();
	}

	@Override
	public Post updatePost(int id, Post updatedPost) {
		Post p = pd.findById(id).orElse(null);
		if (p == null) {
			return null;
		}
		p.setTitle(updatedPost.getTitle());
		p.setContent(updatedPost.getContent());
		return pd.save(p);
	}

	@Override
	public void deletePostById(int id) {
		pd.deleteById(id);// inbuilt keyword
	}

	public List<Post> findPostsExcludingUser(int userId) {
		return pd.findByUser_IdNot(userId);
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		return pd.searchPostsByKeyword(keyword);
	}
}
