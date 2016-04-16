package org.kedzo.dreamy.mvc.controllers;

import org.kedzo.dreamy.models.Dream;
import org.kedzo.dreamy.services.impl.DreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by woodman on 16.04.16.
 */

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/dream")
public class DreamController {

    @Autowired
    DreamRepository dreamRepository;

    @RequestMapping(value = "/getByid", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dream getDreamById(@RequestParam long id) {
        return dreamRepository.load(id);
    }
}
