package org.kedzo.dreamy.commons;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kedzo.dreamy.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * DefaultRepositoryService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Mar 21, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class GeneratorTest {

    @Autowired
    private EntityGenerator generator;


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void generateUserTest() throws Exception {
        User user = generator.generateUser();
        Assert.notNull(user);
        Assert.notNull(user.getUsername());
        Assert.notNull(user.getPicture());
        Assert.notNull(user.getPassword());
        Assert.notNull(user.getLogin());
        Assert.notNull(user.getLng());
        Assert.notNull(user.getLat());
        Assert.notNull(user.getIndividual());
        Assert.notNull(user.getEmail());
        Assert.notNull(user.getBirthday());
    }

    @Test
    public void generateDreamTest() throws Exception {
        Dream dream = generator.generateDream();
        Assert.notNull(dream);
        Assert.notNull(dream.getDescription());
    }

    @Test
    public void generateTagTest() throws Exception {
        Tag tag = generator.generateTag();
        Assert.notNull(tag);
        Assert.notNull(tag.getInterpritation());
        Assert.notNull(tag.getPcture());
    }

    @Test
    public void generateEpisodeTest() throws Exception {
        Episode episode = generator.generateEpisode(0);
        Assert.notNull(episode);
        Assert.notNull(episode.getNote());
        Assert.notNull(episode.getOrder());
        Assert.notNull(episode.getPicture());
    }

    @Test
    public void generateBlogTest() throws Exception {
        Blog blog = generator.generateBlog();
        Assert.notNull(blog);
    }

    @Test
    public void generateBlogCommentTest() throws Exception {
        BlogComment blogComment = generator.generateBlogComment();
        Assert.notNull(blogComment);
        Assert.notNull(blogComment.getComment());
    }

    @Test
    public void generateBlogEntityTest() throws Exception {
        BlogEntry blogEntry = generator.generateBlogEntity();
        Assert.notNull(blogEntry);
        Assert.notNull(blogEntry.getTitle());
        Assert.notNull(blogEntry.getText());
        Assert.notNull(blogEntry.getDate());
    }

    @Test
    public void generateEmotionTest() throws Exception {
        EmotionalView view = generator.generateEmotion();
        Assert.notNull(view);
        Assert.notNull(view.getBrightly());
        Assert.notNull(view.getCommon());
        Assert.notNull(view.getFriendly());
        Assert.notNull(view.getUnstable());
        Assert.notNull(view.getOriginally());
    }
}
