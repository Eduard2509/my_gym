package com.alevel.gym.controller;

import com.alevel.gym.dto.CoachDTO;
import com.alevel.gym.dto.SubscriptionDTO;
import com.alevel.gym.model.*;
import com.alevel.gym.service.SubscriptionService;
import com.alevel.gym.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Permission;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pricing")
public class SubscriptionController {

    SubscriptionService subscriptionService;
    VisitorService visitorService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService, VisitorService visitorService) {
        this.subscriptionService = subscriptionService;
        this.visitorService = visitorService;
    }

    @GetMapping
    public ModelAndView getAllSubscriptions(ModelAndView modelAndView) {
        Iterable<Subscription> subscriptions = subscriptionService.getAll();
        modelAndView.addObject("subscriptions", subscriptions);
        modelAndView.setViewName("pricing");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView getSignUp(ModelAndView modelAndView) {
        Subscription subscription = new Subscription();
        modelAndView.addObject("subscription", subscription);
        modelAndView.addObject("namesSubscription", List.of(NamesSubscription.NONE.name(),
                NamesSubscription.OPTIMAL.name(), NamesSubscription.WEEKEND.name(), NamesSubscription.WEEKDAYS,
                NamesSubscription.UNLIMITED.name()));
        modelAndView.setViewName("create-price");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView registrationCoach(@ModelAttribute Subscription subscription, ModelAndView modelAndView) {
        System.out.println(subscription);
        subscriptionService.save(subscription);
        modelAndView.addObject("subscription", subscription);
        modelAndView.setViewName("redirect:/pricing");
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteById(@PathVariable String id, ModelAndView modelAndView) {
        subscriptionService.deleteById(id);
        modelAndView.setViewName("redirect:/pricing");
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateCoach(@PathVariable String id, ModelAndView modelAndView) {
        Subscription subscription = subscriptionService.findById(id);
        modelAndView.addObject("subscription", subscription);
        modelAndView.setViewName("update-price");
        return modelAndView;
    }

    @PutMapping("/update")
    public ModelAndView update(@Valid Subscription subscription, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/pricing");
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        subscriptionService.update(subscription);
        return modelAndView;
    }

    @PatchMapping("/update/{id}")
    public ModelAndView updateMembership(@PathVariable String id ,ModelAndView modelAndView, Principal principal) {
        String name = principal.getName();
        Visitor visitor = visitorService.findByEmail(name);
        System.out.println(visitor);
        Subscription subscription = subscriptionService.findById(id);
        visitor.setSubscription(subscription);
        visitorService.updateVisitor(visitor);
        modelAndView.setViewName("redirect:/pricing");
        return modelAndView;
    }

}
