package org.kedzo.dreamy.services;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kedzo.dreamy.commons.EntityGenerator;
import org.kedzo.dreamy.models.User;
import org.kedzo.dreamy.services.impl.UserRepository;
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
public class UserRepositoryTest {

    private User user1;
    private User user2;
    private User user3;

    @Resource(name = "userRepository")
    private UserRepository repository;

    @Autowired
    private EntityGenerator generator;


    @Before
    public void before() throws Exception {
        user1 = generator.generateUser();
        user2 = generator.generateUser();
        user3 = generator.generateUser();
    }

    @After
    public void after() throws Exception {
        if (repository.load(user1.getId()) != null) {
            repository.delete(user1);
        }
        if (repository.load(user2.getId()) != null) {
            repository.delete(user2);
        }
        if (repository.load(user3.getId()) != null) {
            repository.delete(user3);
        }
    }

    /**
     * Method: save, load, delete
     */
    @Test
    public void testSaveLoadDelete() throws Exception {
        long id = repository.save(user1);
        User load = repository.load(id);
        Assert.assertEquals(user1, load);
        repository.delete(user1);
        load = repository.load(id);
        Assert.assertNull(load);
    }

}
