package tests.api.JSONplaceholderAPI;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import org.example.Framework.api.JSONplaceholderAPI.PostEndPoint;
import org.example.Framework.models.JSONPlaceholderAPImodels.Post;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostApiTests extends BaseTest {

    private PostEndPoint postsEndpoint;

    @BeforeClass
    public void setup() {
        postsEndpoint = new PostEndPoint();
    }

    @Test(description = "Verify that we can get all posts")
    public void testGetAllPosts() {
        List<Post> posts = postsEndpoint.getAllPosts();
        Assert.assertFalse(posts.isEmpty(), "Posts list should not be empty");
    }

    @Test(description = "Verify that we can get a specific post by ID")
    public void testGetPostById() {
        int postId = 1;
        Post post = postsEndpoint.getPostById(postId);

        Assert.assertNotNull(post, "Post should not be null");
        Assert.assertEquals(post.getId().intValue(), postId, "Post ID should match the requested ID");
    }

    @Test(description = "Verify Post Creation with Partial Data")
    public void testCreatePost_partialData(){
        Post post = postsEndpoint.createPost_withPartialData();

        Assert.assertNotNull(post, "Post should not be null");
    }

    @Test(description = "Verify Post Creation with Partial Data")
    public void testCreatePost_fullData(){
        Post post = postsEndpoint.createPost_withFullData(111 , "Adam" , "Adam's New Post");

        Assert.assertNotNull(post, "Post should not be null");
    }

    @Test(description = "Verify Post Update with Partial Data")
    public void testUpdatePost_partialData(){
        Post post = postsEndpoint.updatePost_withPartialData();

        Assert.assertNotNull(post, "Updated post should not be null");
    }

    @Test(description = "Verify Post Update with Full Data")
    public void testUpdatePost_fullData(){
        Post post = postsEndpoint.updatePost_withFullData(111, "Updated Adam", "Adam's Updated Post");

        Assert.assertNotNull(post, "Updated post should not be null");
    }

    @Test(description = "Verify Partial Post Update with Specific Field")
    public void testPartialUpdatePost_partialData(){
        Post post = postsEndpoint.partialUpdatePost_withPartialData();

        Assert.assertNotNull(post, "Partially updated post should not be null");
    }

    @Test(description = "Verify Partial Post Update with Specific Fields")
    public void testPartialUpdatePost_specificData(){
        Map<String, Object> fieldsToUpdate = new HashMap<>();
        fieldsToUpdate.put("title", "New Title Only");

        Post post = postsEndpoint.partialUpdatePost_withSpecificData(1, fieldsToUpdate);

        Assert.assertNotNull(post, "Partially updated post should not be null");
        Assert.assertEquals(post.getTitle(), "New Title Only", "Title should be updated");
    }

}
