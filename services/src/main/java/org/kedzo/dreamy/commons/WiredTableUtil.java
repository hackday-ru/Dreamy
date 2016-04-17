package org.kedzo.dreamy.commons;

import org.kedzo.dreamy.models.Dream;
import org.kedzo.dreamy.models.DreamType;
import org.kedzo.dreamy.models.Episode;
import org.kedzo.dreamy.models.Tag;
import org.kedzo.dreamy.services.impl.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

@Component
public class WiredTableUtil {

    @Resource(name = "dreamRepository")
    private DreamRepository dreamRepository;

    @Resource(name = "tagRepository")
    private TagRepository tagRepository;

    @Resource(name = "episodeTagsRepository")
    private EpisodeTagsRepository episodeTagsRepository;

    @Resource(name = "dreamDTypeRepository")
    private DreamDTypeRepository typeRepository;

    @Resource(name = "dreamTypeRepository")
    private DreamTypeRepository repository;

    public static void main(String[] args) {
        System.out.println("Start");
        WiredTableUtil bean = new ClassPathXmlApplicationContext("spring-config.xml").getBean(WiredTableUtil.class);
        bean.wired();
        System.out.println("Stop");
    }

    public void wired() {
        Set<Episode> episodeSet = dreamRepository.getAllEpisodes();
        for (Episode episode : episodeSet) {
            Set<Tag> randTags = tagRepository.getRandTags();
            for (Tag randTag : randTags) {
                episodeTagsRepository.insert(episode.getId(), randTag.getId());
            }

        }

        Set<Dream> dreamSet = dreamRepository.getAllDreams();
        for (Dream dream : dreamSet) {
            Set<DreamType> dreamTypes = repository.getRandDreamType();
            for (DreamType dreamType : dreamTypes) {
                typeRepository.insert(dream.getId(), dreamType.getId());
            }

        }

    }
}
