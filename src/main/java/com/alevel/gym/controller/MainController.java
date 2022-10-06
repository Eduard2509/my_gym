package com.alevel.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class MainController {

    @GetMapping("/")
    public ModelAndView getMenu(ModelAndView modelAndView) {
        modelAndView.setViewName("menu");
        return modelAndView;
    }

    @GetMapping("/about")
    public ModelAndView getAbout(ModelAndView modelAndView) {
        modelAndView.setViewName("about");
        return modelAndView;
    }

    @GetMapping("/coaches")
    public ModelAndView getCoaches(ModelAndView modelAndView) {
        modelAndView.setViewName("coaches");
        return modelAndView;
    }

    @GetMapping("/pricing")
    public ModelAndView getPricing(ModelAndView modelAndView) {
        modelAndView.setViewName("pricing");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView getLogin(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/sign-up")
    public ModelAndView getSignUp(ModelAndView modelAndView) {
        modelAndView.setViewName("sign-up");
        return modelAndView;
    }
}
