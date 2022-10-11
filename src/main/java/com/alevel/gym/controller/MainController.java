package com.alevel.gym.controller;

import com.alevel.gym.dto.VisitorDTO;
import com.alevel.gym.mapper.VisitorMapper;
import com.alevel.gym.model.Sex;
import com.alevel.gym.model.StatusPeople;
import com.alevel.gym.model.Visitor;
import com.alevel.gym.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class MainController {

    VisitorService visitorService;

    @Autowired
    public MainController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

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
        Visitor visitor = new Visitor();
        modelAndView.addObject("visitor", visitor);
        modelAndView.addObject("sexes", List.of(Sex.MAN.name(), Sex.WOMAN.name()));
        modelAndView.setViewName("sign-up");
        return modelAndView;
    }

    @PostMapping("/sign-up")
    public ModelAndView registrationVisitor(@ModelAttribute VisitorDTO visitorDTO, ModelAndView modelAndView) {
        System.out.println(visitorDTO);
        visitorService.save(visitorDTO);
        modelAndView.addObject("visitor", visitorDTO);
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
