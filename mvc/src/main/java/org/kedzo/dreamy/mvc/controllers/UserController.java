package org.kedzo.dreamy.mvc.controllers;

import org.kedzo.dreamy.models.User;
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
import java.util.OptionalLong;

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(
            value = "/signup",
            method = RequestMethod.GET
    )
    public ModelAndView signup() {
        return new ModelAndView("/signup");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        userRepository.update(user);
        return user;
    }

    @RequestMapping(
            value = "/signup",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            method = RequestMethod.POST)
    public String signup(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            HttpServletResponse response) {

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(email.split("@")[0]);
        long userId = userRepository.save(user);
        response.addCookie(new Cookie("user", String.valueOf(userId)));


        return "redirect:/user/info";
    }

    @RequestMapping(value = "/numberOfUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Integer numberUsers() {
        return userRepository.getNumberUsers();
    }



    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public User getUserInfo(final HttpServletRequest request) {
        OptionalLong userId = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("user"))
                .mapToLong(cookie -> Long.valueOf(cookie.getValue()))
                .findFirst();

        if (userId.isPresent()) {
            return userRepository.load(userId.getAsLong());
        }
        return null;
    }
}
