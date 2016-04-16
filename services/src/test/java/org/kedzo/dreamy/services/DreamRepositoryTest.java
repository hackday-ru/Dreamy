package org.kedzo.dreamy.services;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kedzo.dreamy.commons.EntityGenerator;
import org.kedzo.dreamy.services.impl.DreamRepository;
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
public class DreamRepositoryTest {

    private Dream dream1;
    private Dream dream2;
    private Dream dream3;

    @Resource(name = "dreamRepository")
    private DreamRepository repository;

    @Autowired
    private EntityGenerator generator;


    @Before
    public void before() throws Exception {
        dream1 = generator.generateDream();
        dream2 = generator.generateDream();
        dream3 = generator.generateDream();
    }

    @After
    public void after() throws Exception {
        repository.delete(dream1);
        repository.delete(dream2);
        repository.delete(dream3);
    }

    /**
     * Method: save, load, delete
     */
    @Test
    public void testSaveLoadDelete() throws Exception {
        long id = repository.save(dream1);
        User loadUser = repository.load(id);
        Assert.assertEquals(dream1, loadUser);
        repository.delete(dream1);
        Assert.assertEquals(loadUser, deleteEmployee);
        loadUser = repository.load(id);
        Assert.assertNull(loadUser);
    }

}
