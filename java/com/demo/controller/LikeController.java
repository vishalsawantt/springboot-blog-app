package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.LikeService;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/toggle")
    public ResponseEntity<Map<String, Object>> toggleLike(@RequestParam int postId, @RequestParam int userId) {
        int count = likeService.toggleLike(postId, userId);
        boolean liked = likeService.isLiked(postId, userId);

        Map<String, Object> res = new HashMap<>();
        res.put("count", count);
        res.put("liked", liked);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/count")
    public int getCount(@RequestParam int postId) {
        return likeService.getLikeCount(postId);
    }
    
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getLikeStatus(@RequestParam int postId, @RequestParam int userId) {
        int count = likeService.getLikeCount(postId);
        boolean liked = likeService.isLiked(postId, userId);

        Map<String, Object> res = new HashMap<>();
        res.put("likeCount", count);
        res.put("liked", liked);
        return ResponseEntity.ok(res);
    }

}
