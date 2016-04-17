package org.kedzo.dreamy.mvc.controllers;

import org.apache.commons.lang3.StringUtils;
import org.kedzo.dreamy.models.Tag;
import org.kedzo.dreamy.services.impl.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.tartarus.snowball.ext.russianStemmer;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION)
public class InterpretationController {

    @Autowired
    private TagRepository tagRepository;

    @RequestMapping(value = "/interpret", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> getInterpretation(@RequestParam String search) {
        String[] searchTerms = search.split(",");
        List<String> stemmedTerms = Arrays.stream(searchTerms).map(term -> {
            russianStemmer stemmer = new russianStemmer();
            stemmer.setCurrent(term.toLowerCase());
            stemmer.stem();
            return stemmer.getCurrent();
        }).collect(Collectors.toList());

        Set<Tag> tagsByTerms = tagRepository.getTagsByTerms(stemmedTerms);

        return tagsByTerms.stream()
                .map(tag -> StringUtils.capitalize(tag.getInterpritation()))
                .collect(Collectors.toList());
    }
}
