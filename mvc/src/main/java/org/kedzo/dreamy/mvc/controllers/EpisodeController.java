package org.kedzo.dreamy.mvc.controllers;

import org.kedzo.dreamy.models.Dream;
import org.kedzo.dreamy.models.Episode;
import org.kedzo.dreamy.models.Tag;
import org.kedzo.dreamy.services.impl.DreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by woodman on 17.04.16.
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/episode")
public class EpisodeController {
    @Autowired
    DreamRepository dreamRepository;

    @RequestMapping(value = "/drop", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Episode deleteEpisode(@RequestParam long dreamId,
                                 @RequestParam long episodeId) {
        Dream dream = dreamRepository.load(dreamId);
        Set<Episode> dreamEpisodes = dream.getEpisodes();
        for (Episode episode : dreamEpisodes) {
            if (episodeId == episode.getId()) {
                dreamEpisodes.remove(episode);
                dream.setEpisodes(dreamEpisodes);
                dreamRepository.save(dream);
                return episode;
            }
        }
        return null;
    }

    @RequestMapping(value = "/addEpisode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Episode addEpisode(@RequestParam String picture,
                              @RequestParam String note,
                              @RequestParam int order,
                              @RequestParam String tags,
                              @RequestParam long dreamId) {
        Dream dream = dreamRepository.load(dreamId);
        Episode episode = new Episode();
        Set<String> intepritationTag = Arrays.stream(tags.split(",")).collect(Collectors.toSet());
        Set<Tag> episodeTags = new HashSet<>();
        for (String intepritation : intepritationTag) {
            episodeTags.add(new Tag(intepritation));
        }
        episode.setNote(note);
        episode.setOrder(order);
        episode.setPicture(picture);
        episode.setTags(episodeTags);
        dream.getEpisodes().add(episode);
        dreamRepository.update(dream);
        return episode;
    }


}
