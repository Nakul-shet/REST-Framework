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

    public Post createPost_withPartialData(){
        Post newPost = new Post();
        newPost.setUserId(21);
        newPost.setTitle("Test Title 2");
        newPost.setBody("Test Body 2");
        return apiClient.create(POSTS_ENDPOINT , newPost , Post.class);
    }

    public Post createPost_withFullData(Integer userId , String title , String body){
        Post newPost = new Post(userId , title , body);
        return apiClient.create(POSTS_ENDPOINT , newPost , Post.class);
    }

    public Post updatePost_withPartialData() {
        Post updatedPost = new Post();
        updatedPost.setUserId(21);
        updatedPost.setTitle("Updated Title");
        updatedPost.setBody("Updated Body");
        return apiClient.update(POSTS_ENDPOINT + "/1", updatedPost, Post.class);
    }

    public Post updatePost_withFullData(Integer userId, String title, String body) {
        Post updatedPost = new Post(userId, title, body);
        return apiClient.update(POSTS_ENDPOINT + "/1", updatedPost, Post.class);
    }

    public Post partialUpdatePost_withPartialData() {
        Post partialPost = new Post();
        partialPost.setTitle("Partially Updated Title");
        // Only updating the title, leaving other fields unchanged
        return apiClient.partialUpdate(POSTS_ENDPOINT + "/1", partialPost, Post.class);
    }

    public Post partialUpdatePost_withSpecificData(Integer postId, Map<String, Object> fieldsToUpdate) {
        // Create a partial object with only the fields that need to be updated
        Post partialPost = new Post();

        if (fieldsToUpdate.containsKey("userId")) {
            partialPost.setUserId((Integer) fieldsToUpdate.get("userId"));
        }

        if (fieldsToUpdate.containsKey("title")) {
            partialPost.setTitle((String) fieldsToUpdate.get("title"));
        }

        if (fieldsToUpdate.containsKey("body")) {
            partialPost.setBody((String) fieldsToUpdate.get("body"));
        }

        return apiClient.partialUpdate(POSTS_ENDPOINT + "/" + postId, partialPost, Post.class);
    }

}
