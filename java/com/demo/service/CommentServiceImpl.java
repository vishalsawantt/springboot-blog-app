package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.CommentDao;
import com.demo.model.Comment;
import com.demo.model.Post;
import com.demo.model.User;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao cd;

    @Autowired
    private UserService us;

    @Autowired
    private PostService ps;

    @Override
    public void addComment(String commentText, int postId, int userId) {
        Post post = ps.findById(postId);
        User user = us.getUserById(userId);

        Comment comment = new Comment();
        comment.setComment(commentText);
        comment.setPost(post);
        comment.setUser(user);

        cd.save(comment);
    }

    @Override
    public Comment updateComment(int commentId, String updatedCommentText) {
        Comment existingComment = cd.findById(commentId)
            .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));

        existingComment.setComment(updatedCommentText);
        return cd.save(existingComment);
    }


    @Override
    public void deleteComment(int commentId) {
        if (!cd.existsById(commentId)) {
            throw new RuntimeException("Comment not found with id: " + commentId);
        }
        cd.deleteById(commentId);
    }

    @Override
    public List<Comment> findAll() {
        return cd.findAll();
    }

	@Override
	public List<Comment> getCommentsByPostId(int postId) {
		return cd.findByPost_PostId(postId);
	}
	
	@Override
	public Page<Comment> getCommentsByPost(int postId, Pageable pageable) {
		return cd.findByPost_PostId(postId, pageable);
	}

}
