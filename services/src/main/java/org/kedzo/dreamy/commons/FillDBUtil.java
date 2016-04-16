package org.kedzo.dreamy.commons;

import org.kedzo.dreamy.models.*;
import org.kedzo.dreamy.services.impl.BlogRepository;
import org.kedzo.dreamy.services.impl.DreamTypeRepository;
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

    @Resource(name = "dreamTypeRepository")
    private DreamTypeRepository dreamTypeRepository;

    @Resource(name = "tagRepository")
    private TagRepository tagRepository;

    @Resource(name = "blogRepository")
    private BlogRepository blogRepository;

    public static void main(String[] args) {
        FillDBUtil fillDBUtil = new ClassPathXmlApplicationContext("spring-config.xml").getBean(FillDBUtil.class);
        fillDBUtil.fillDB(10);
    }

    private void fillDB(int entry) {
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
                    episode.setTags(getTags());
                    episodes.add(episode);
                }
                dream.setEpisodes(episodes);
                dream.setHappy(getDreamType());
                dreams.add(dream);
            }
            user.setDreams(dreams);
            EmotionalView emotionalView = generator.generateEmotion();
            emotionalView.setHappy(getHappy(dreams));
            emotionalView.setOriginally(getOriginally(dreams));
            user.setEmotionalViews(emotionalView);
            users.add(user);
        }
        for (User user : users) {
            userRepository.save(user);
        }

        System.out.println("End fill db");
    }

    private Set<Tag> getTags() {
        return tagRepository.getRandTags();
    }

    private Set<DreamType> getDreamType() {
        return dreamTypeRepository.getRandDreamType();
    }

    private double getHappy(Set<Dream> dreams) {
        return dreams.stream()
                .mapToDouble(dream -> dream.getHappy().stream()
                        .mapToInt(DreamType::getWeight)
                        .average()
                        .orElse(0.0))
                .average()
                .orElse(0.0);
    }

    private double getOriginally(Set<Dream> dreams) {
        return dreams.stream().mapToInt(dream -> dream.getEpisodes().size()).average().orElse(0.0);
    }

}
