package org.kedzo.dreamy.mvc.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

@Controller
@Scope(value = WebApplicationContext.SCOPE_SESSION)
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/signup")
    public String getUserInfo() {
        return "user info";
    }

}
