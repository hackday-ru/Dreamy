package org.kedzo.dreamy.services;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kedzo.dreamy.commons.EntityGenerator;
import org.kedzo.dreamy.models.Dream;
import org.kedzo.dreamy.services.impl.DreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Collections;

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
        dream1.setEpisodes(Collections.singleton(generator.generateEpisode()));
        dream2 = generator.generateDream();
        dream2.setEpisodes(Collections.singleton(generator.generateEpisode()));
        dream3 = generator.generateDream();
        dream3.setEpisodes(Collections.singleton(generator.generateEpisode()));
    }

    @After
    public void after() throws Exception {
        if (repository.load(dream1.getId()) != null) {
            repository.delete(dream1);
        }
        if (repository.load(dream2.getId()) != null) {
            repository.delete(dream2);
        }
        if (repository.load(dream3.getId()) != null) {
            repository.delete(dream3);
        }

    }

    /**
     * Method: save, load, delete
     */
    @Test
    public void testSaveLoadDelete() throws Exception {
        long id = repository.save(dream1);
        Dream load = repository.load(id);
        Assert.assertEquals(dream1, load);
        repository.delete(dream1);
        load = repository.load(id);
        Assert.assertNull(load);
    }

}
