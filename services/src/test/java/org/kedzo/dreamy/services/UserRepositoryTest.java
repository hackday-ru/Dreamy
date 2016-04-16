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
    private UserRepository userRepository;

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
        userRepository.delete(user1);
        userRepository.delete(user2);
        userRepository.delete(user3);
    }

    /**
     * Method: save, load, delete
     */
    @Test
    public void testSaveLoadDelete() throws Exception {
        long id = userRepository.save(user1);
        User load = userRepository.load(id);
        Assert.assertEquals(user1, load);
        userRepository.delete(user1);
        load = userRepository.load(id);
        Assert.assertNull(load);
    }

}
