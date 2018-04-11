package com.attendance.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FantasticPan on 2018/3/23.
 */
@Controller
public class IndexController {

    @RequestMapping("/personal_center")
    public String login(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
            return "managerSystem";
        } else if (auth.getAuthorities().toString().equals("[ROLE_SIGN]")) {
            return "main";
        }
        return "personalSystem";
    }
}
