package tests.api.JSONplaceholderAPI;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import org.example.Framework.api.JSONplaceholderAPI.PostEndPoint;
import org.example.Framework.models.JSONPlaceholderAPImodels.Post;
import org.testng.Assert;

import java.util.List;

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

}
