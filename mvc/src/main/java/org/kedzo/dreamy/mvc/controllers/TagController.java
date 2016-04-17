package org.kedzo.dreamy.mvc.controllers;

import org.kedzo.dreamy.models.Dream;
import org.kedzo.dreamy.models.Episode;
import org.kedzo.dreamy.models.responce.PopularTagResponce;
import org.kedzo.dreamy.models.Tag;
import org.kedzo.dreamy.models.User;
import org.kedzo.dreamy.services.impl.DreamRepository;
import org.kedzo.dreamy.services.impl.TagRepository;
import org.kedzo.dreamy.services.impl.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by woodman on 17.04.16.
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagRepository tagRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DreamRepository dreamRepository;

    @RequestMapping(value = "/popular", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Set<PopularTagResponce> getPopularTag() {
        Random random = new Random();
        Set<Tag> tags = tagRepository.getRandTags();
        Set<PopularTagResponce> responces = new HashSet<>();
        tags.stream().forEach(tag -> {
            responces.add(new PopularTagResponce(random.nextInt(20), tag));
        });
        return responces;
    }

    @RequestMapping(value = "/popularForUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Set<PopularTagResponce> getPopularTagForUser(@RequestParam long userId) {
        User user = userRepository.load(userId);
        Set<Dream> userDreams = userRepository.getAllDreams(user);
        Set<PopularTagResponce> responces = new HashSet<>();
        Map<Tag, Integer> result = new HashMap<>();
        userDreams.stream().forEach(dream -> {
            dream.getEpisodes().stream().forEach(episode -> {
                episode.getTags().stream().forEach(tag -> {
                    if (result.get(tag) != null) {
                        result.put(tag, result.get(tag) + 1);
                    } else {
                        result.put(tag, 1);
                    }
                });
            });
        });
        result.entrySet().stream().map(e -> {
            responces.add(new PopularTagResponce(e.getValue(), e.getKey()));
            return e;
        });
        return responces;

    }

    @RequestMapping(value = "/middlingTagForUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Double middlingTagForUser(@RequestParam long userId) {
        User user = userRepository.load(userId);
        Set<Dream> dreams = userRepository.getAllDreams(user);
        Map<Set<Episode>, Integer> result = new HashMap<>();
        dreams.stream().forEach(dream -> {
            Set<Episode> episodes = dreamRepository.getAllEpisodes();
            int quantity = 0;
            for (Episode episode : episodes) {
                quantity = quantity + episode.getTags().size();
            }
            result.put(episodes, quantity);
        });
        double response = 0.0;
        for (Integer integer : result.values()) {
            response += integer;
        }
        return response / result.size();
    }
}
