package org.example.Framework.api.JSONplaceholderAPI;

import org.example.Framework.api.BaseAPIClient;
import org.example.Framework.models.JSONPlaceholderAPImodels.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PostEndPoint {

    private static final String POSTS_ENDPOINT = "posts";
    private final BaseAPIClient apiClient;

    public PostEndPoint() {
        this.apiClient = new BaseAPIClient();
    }

    public List<Post> getAllPosts() {
        System.out.println("Getting All Posts");
        return apiClient.getAll(POSTS_ENDPOINT, Post[].class);
    }

    public List<Post> getPostsByUserId(int userId) {
        System.out.println("Get Post by User ID");
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("userId", userId);
        return apiClient.getAllWithQueryParams(POSTS_ENDPOINT, queryParams, Post[].class);
    }

    public Post getPostById(int postId) {
        return apiClient.getById(POSTS_ENDPOINT, postId, Post.class);
    }

}
