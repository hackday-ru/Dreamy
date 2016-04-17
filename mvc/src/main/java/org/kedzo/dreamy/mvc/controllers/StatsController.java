package org.kedzo.dreamy.mvc.controllers;

import org.kedzo.dreamy.services.impl.DreamRepository;
import org.kedzo.dreamy.services.impl.EpisodeTagsRepository;
import org.kedzo.dreamy.services.impl.TagRepository;
import org.kedzo.dreamy.services.impl.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    DreamRepository dreamRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private EpisodeTagsRepository episodeTagsRepository;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getStat() {
        return new ModelAndView("/stats");
    }

}
