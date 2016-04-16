package org.kedzo.dreamy.commons;

import org.kedzo.dreamy.models.Dream;
import org.kedzo.dreamy.models.Episode;
import org.kedzo.dreamy.models.User;
import org.kedzo.dreamy.services.impl.BlogRepository;
import org.kedzo.dreamy.services.impl.DreamRepository;
import org.kedzo.dreamy.services.impl.TagRepository;
import org.kedzo.dreamy.services.impl.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class FillDBUtil {
    @Autowired
    private EntityGenerator generator;

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource(name = "dreamRepository")
    private DreamRepository dreamRepository;

    @Resource(name = "tagRepository")
    private TagRepository tagRepository;

    @Resource(name = "blogRepository")
    private BlogRepository blogRepository;

    public static void main(String[] args) {
        FillDBUtil fillDBUtil = new ClassPathXmlApplicationContext("spring-config.xml").getBean(FillDBUtil.class);
        fillDBUtil.fillDB(10);
    }

    public void fillDB(int entry) {
        System.out.println("Start fill db");
        Set<User> users = new HashSet<>();
        for (int t = 0; t < entry; t++) {
            User user = generator.generateUser();
            Set<Dream> dreams = new HashSet<>();
            for (int i = 0; i < entry; i++) {
                Dream dream = generator.generateDream();
                dream.setDate(new Date(new Date().getTime() - TimeUnit.DAYS.toMillis(i)));
                Set<Episode> episodes = new HashSet<>();
                for (int j = 0; j < entry; j++) {
                    Episode episode = generator.generateEpisode(i);
                    episodes.add(episode);
                }
                dream.setEpisodes(episodes);
                dreams.add(dream);
            }
            user.setDreams(dreams);
            users.add(user);
        }
        for (User user : users) {
            userRepository.save(user);
        }

        System.out.println("End fill db");
    }

}
