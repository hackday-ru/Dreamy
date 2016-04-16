package org.kedzo.dreamy.services;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kedzo.dreamy.commons.EntityGenerator;
import org.kedzo.dreamy.models.Tag;
import org.kedzo.dreamy.services.impl.TagRepository;
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
public class TagRepositoryTest {

    private Tag tag1;
    private Tag tag2;
    private Tag tag3;

    @Resource(name = "tagRepository")
    private TagRepository repository;

    @Autowired
    private EntityGenerator generator;


    @Before
    public void before() throws Exception {
        tag1 = generator.generateTag();
        tag2 = generator.generateTag();
        tag3 = generator.generateTag();
    }

    @After
    public void after() throws Exception {
        if (repository.load(tag1.getId()) != null) {
            repository.delete(tag1);
        }
        if (repository.load(tag2.getId()) != null) {
            repository.delete(tag2);
        }
        if (repository.load(tag3.getId()) != null) {
            repository.delete(tag3);
        }
    }

    /**
     * Method: save, load, delete
     */
    @Test
    public void testSaveLoadDelete() throws Exception {
        long id = repository.save(tag1);
        Tag load = repository.load(id);
        Assert.assertEquals(tag1, load);
        repository.delete(tag1);
        load = repository.load(id);
        Assert.assertNull(load);
    }

}
