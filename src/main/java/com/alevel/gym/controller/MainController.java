package com.alevel.gym.controller;

import com.alevel.gym.model.*;
import com.alevel.gym.service.CoachService;
import com.alevel.gym.service.SubscriptionService;
import com.alevel.gym.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;

@Controller
@RequestMapping
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    VisitorService visitorService;
    CoachService coachService;
    SubscriptionService subscriptionService;

    @Autowired
    public MainController(VisitorService visitorService, CoachService coachService, SubscriptionService subscriptionService) {
        this.visitorService = visitorService;
        this.coachService = coachService;
        this.subscriptionService = subscriptionService;
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
    public ModelAndView registrationVisitor(@ModelAttribute @Valid Visitor visitor, BindingResult bindingResult, ModelAndView modelAndView) {

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("sign-up");
            return modelAndView;
        }

        if (visitorService.findByEmail(visitor.getEmail()) == null) {
            Subscription byName = subscriptionService.findByName(NamesSubscription.NONE);
            Coach noneCoach = coachService.findByName("NONE");
            visitor.setSubscription(byName);
            visitor.setCoach(noneCoach);
            visitorService.saveVisitor(visitor);
            LOGGER.info("Visitor created {}", visitor.getName());
            modelAndView.addObject("visitor", visitor);
            modelAndView.setViewName("login");
            return modelAndView;
        } else {
            modelAndView.setViewName("sign-up");
            LOGGER.info("Fail create visitor");
            return modelAndView;
        }
    }
}
