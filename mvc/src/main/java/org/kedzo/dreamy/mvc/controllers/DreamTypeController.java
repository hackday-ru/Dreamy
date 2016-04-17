package org.kedzo.dreamy.mvc.controllers;

import org.kedzo.dreamy.models.Dream;
import org.kedzo.dreamy.models.DreamType;
import org.kedzo.dreamy.models.User;
import org.kedzo.dreamy.models.responce.DreamTypeResponce;
import org.kedzo.dreamy.services.impl.DreamRepository;
import org.kedzo.dreamy.services.impl.DreamTypeRepository;
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
import java.util.Set;

/**
 * Created by woodman on 17.04.16.
 */
@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/dreamType")
public class DreamTypeController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DreamRepository dreamRepository;
    @Autowired
    DreamTypeRepository dreamTypeRepository;

    @RequestMapping(value = "/dreamTypesByUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Set<DreamTypeResponce> getDreamTypesForUser(@RequestParam long userid) {
        User user = userRepository.load(userid);
        Set<Dream> userDreams = userRepository.getAllDreams(user);
        Set<DreamTypeResponce> responces = new HashSet<>();
        Map<DreamType, Integer> result = new HashMap<>();
        userDreams.stream().forEach(dream -> {
            dreamTypeRepository.getAllDreamsTypes(dream).stream().forEach(dreamType -> {
                if (result.get(dreamType) != null) {
                    result.put(dreamType, result.get(dreamType) + 1);
                } else {
                    result.put(dreamType, 1);
                }
            });
        });
        result.entrySet().stream().map(e -> {
            responces.add(new DreamTypeResponce(e.getKey(), e.getValue()));
            return e;
        });
        return responces;
    }
}
