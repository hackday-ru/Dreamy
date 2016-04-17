package org.kedzo.dreamy.mvc.controllers;

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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by woodman on 16.04.16.
 */

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
            dream.addEpisode(episode);
            dreamRepository.update(dream);
        }

        Set<Tag> tags = tagRepository.getTagsByTerms(episodeRequest.getTags());
        tags.forEach(tag -> {
            episodeTagsRepository.insert(episode.getId(), tag.getId());
        });

        return tags.stream().map(Tag::getPcture).collect(Collectors.toList());
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
