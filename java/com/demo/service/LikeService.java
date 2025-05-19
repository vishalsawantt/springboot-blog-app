package com.demo.service;

public interface LikeService {
	int countByPost_PostId(int postId);

	boolean existsByUser_UserIdAndPost_PostId(int userId, int postId);

	void deleteByUser_UserIdAndPost_PostId(int userId, int postId);

	int toggleLike(int postId, int userId);

	boolean isLiked(int postId, int userId);

	int getLikeCount(int postId);

}
