package org.kedzo.dreamy.mvc.controllers;

import org.apache.commons.lang3.StringUtils;
import org.kedzo.dreamy.models.Dream;
import org.kedzo.dreamy.models.Episode;
import org.kedzo.dreamy.models.Tag;
import org.kedzo.dreamy.models.User;
import org.kedzo.dreamy.mvc.dto.EpisodeRequest;
import org.kedzo.dreamy.services.impl.DreamRepository;
import org.kedzo.dreamy.services.impl.EpisodeTagsRepository;
import org.kedzo.dreamy.services.impl.TagRepository;
import org.kedzo.dreamy.services.impl.UserRepository;
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
import org.springframework.web.servlet.ModelAndView;
import org.tartarus.snowball.ext.russianStemmer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/dream")
public class DreamController {

    @Autowired
    DreamRepository dreamRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private EpisodeTagsRepository episodeTagsRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getDream() {
        return new ModelAndView("/dream");
    }

    @RequestMapping(value = "/getByid", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Dream getDreamById(@RequestParam long dreamId) {
        return dreamRepository.load(dreamId);
    }

    @RequestMapping(value = "/getByUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Set<Dream> getDreamsByUserId(@RequestParam long userId) {
        User user = userRepository.load(userId);
        return user.getDreams();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Dream deleteById(@RequestParam long dreamId) {
        Dream dream = dreamRepository.load(dreamId);
        dreamRepository.delete(dream);
        return dream;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void addDream(HttpServletResponse response) {
        Dream dream = new Dream();
        dream.setDate(new Date());
        long dreamId = dreamRepository.save(dream);
        response.addCookie(new Cookie("dreamId", String.valueOf(dreamId)));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Dream updateDream(@RequestBody Dream dream) {
        dreamRepository.update(dream);
        return dream;
    }

    @RequestMapping(value = "/addEpisode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> addEpisode(
            @RequestBody EpisodeRequest episodeRequest,
            HttpServletRequest httpRequest
    ) {
        Episode episode = new Episode();
        episode.setOrder(episodeRequest.getOrder());
        String dreamId = getCookie(httpRequest, "dreamId");
        if (dreamId != null ) {
            Dream dream = dreamRepository.load(Long.valueOf(dreamId));
            Set<Episode> episodes = dream.getEpisodes();
            episodes.add(episode);
            dream.setEpisodes(episodes);
            dreamRepository.update(dream);
        }

        List<String> terms = episodeRequest.getTags()
                .stream()
                .map(e -> {
                    russianStemmer stemmer = new russianStemmer();
                    stemmer.setCurrent(e.toLowerCase());
                    stemmer.stem();
                    return stemmer.getCurrent();
                }).collect(Collectors.toList());

        Set<Tag> tags = tagRepository.getTagsByTerms(terms);
        tags.forEach(tag -> {
            episodeTagsRepository.insert(episode.getId(), tag.getId());
        });

        return tags.stream().map(Tag::getPcture).collect(Collectors.toList());
    }

    @RequestMapping(value = "/interpret", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<List<String>> getInterpretation(HttpServletRequest request) {
        String dreamId = getCookie(request, "dreamId");
        if (dreamId != null) {
            Dream dream = dreamRepository.load(Long.valueOf(dreamId));

            List<Tag> tags = dream.getEpisodes().stream()
                    .flatMap(e -> episodeTagsRepository.getTagsByEpisodeId(e.getId()).stream())
                    .collect(Collectors.toList());

            return tags.stream().map(tag -> {
                List<String> resp = new ArrayList<>();
                resp.add(tag.getPcture());
                resp.add(tag.getInterpritation());
                return resp;
            }).collect(Collectors.toList());
        }

        return null;
    }

    @RequestMapping(value = "/journal", method = RequestMethod.GET)
    public ModelAndView getJournal() {
        return new ModelAndView("/journal-dream");
    }

    private String getCookie(HttpServletRequest request, String cookieName) {
        Optional<Cookie> optionalCookie = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findFirst();
        if (optionalCookie.isPresent()) {
            return optionalCookie.get().getValue();
        }
        return null;
    }
}
