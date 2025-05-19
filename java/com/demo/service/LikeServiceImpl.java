package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.LikeDao;
import com.demo.dao.PostDao;
import com.demo.dao.UserDao;
import com.demo.model.PostLike;

@Service
@Transactional
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeDao likeRepo;

	@Autowired
	private UserDao userRepo;

	@Autowired
	private PostDao postRepo;

	@Override
	public int countByPost_PostId(int postId) {
		return likeRepo.countByPost_PostId(postId);
	}

	@Override
	public boolean existsByUser_UserIdAndPost_PostId(int userId, int postId) {
		return likeRepo.existsByUser_IdAndPost_PostId(userId, postId);
	}

	@Override
	public void deleteByUser_UserIdAndPost_PostId(int userId, int postId) {
		likeRepo.deleteByUser_IdAndPost_PostId(userId, postId);
	}

	// Toggle like: if exists, remove it; else, add like
	public int toggleLike(int postId, int userId) {
		if (likeRepo.existsByUser_IdAndPost_PostId(userId, postId)) {
			likeRepo.deleteByUser_IdAndPost_PostId(userId, postId);
		} else {
			PostLike like = new PostLike();
			like.setUser(userRepo.findById(userId).orElseThrow());
			like.setPost(postRepo.findById(postId).orElseThrow());
			likeRepo.save(like);
		}
		return likeRepo.countByPost_PostId(postId);
	}

	public boolean isLiked(int postId, int userId) {
		return likeRepo.existsByUser_IdAndPost_PostId(userId, postId);
	}

	public int getLikeCount(int postId) {
		return likeRepo.countByPost_PostId(postId);
	}
}
