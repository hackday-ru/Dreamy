package org.kedzo.dreamy.services;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kedzo.dreamy.commons.EntityGenerator;
import org.kedzo.dreamy.models.Blog;
import org.kedzo.dreamy.services.impl.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * DefaultRepositoryService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Mar 21, 2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class BlogRepositoryTest {

    private Blog blog1;
    private Blog blog2;
    private Blog blog3;

    @Resource(name = "blogRepository")
    private BlogRepository repository;

    @Autowired
    private EntityGenerator generator;


    @Before
    public void before() throws Exception {
        blog1 = generator.generateBlog();
        blog2 = generator.generateBlog();
        blog3 = generator.generateBlog();
    }

    @After
    public void after() throws Exception {
        repository.delete(blog1);
        repository.delete(blog2);
        repository.delete(blog3);
    }

    /**
     * Method: save, load, delete
     */
    @Test
    public void testSaveLoadDelete() throws Exception {
        long id = repository.save(blog1);
        Blog load = repository.load(id);
        Assert.assertEquals(blog1, load);
        repository.delete(blog1);
        load = repository.load(id);
        Assert.assertNull(load);
    }

}
