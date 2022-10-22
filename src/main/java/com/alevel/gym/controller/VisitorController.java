package com.alevel.gym.controller;

import com.alevel.gym.Main;
import com.alevel.gym.dto.VisitorDTO;
import com.alevel.gym.mapper.VisitorMapper;
import com.alevel.gym.model.*;
import com.alevel.gym.service.CoachService;
import com.alevel.gym.service.SubscriptionService;
import com.alevel.gym.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
@RequestMapping("/visitors")
public class VisitorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisitorController.class);

    VisitorService visitorService;
    SubscriptionService subscriptionService;
    CoachService coachService;

    @Autowired
    public VisitorController(VisitorService visitorService, SubscriptionService subscriptionService, CoachService coachService) {
        this.visitorService = visitorService;
        this.subscriptionService = subscriptionService;
        this.coachService = coachService;
    }

    @GetMapping
    public ModelAndView returnTest(ModelAndView modelAndView) {
        List<Visitor> allVisitors = visitorService.findAllVisitors();
        modelAndView.addObject("visitors", allVisitors);
        modelAndView.setViewName("visitors");
        return modelAndView;
    }



    @GetMapping("/create")
    public ModelAndView getSignUp(ModelAndView modelAndView) {
        Visitor visitor = new Visitor();
        modelAndView.addObject("visitor", visitor);
        modelAndView.addObject("sexes", List.of(Sex.MAN.name(), Sex.WOMAN.name()));
        modelAndView.setViewName("create-visitor");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView registrationVisitor(@ModelAttribute VisitorDTO visitorDTO, ModelAndView modelAndView) {
        if (visitorService.findByEmail(visitorDTO.getEmail()) == null) {
            Subscription byName = subscriptionService.findByName(NamesSubscription.NONE);
            Coach noneCoach = coachService.findByName("NONE");
            visitorDTO.setSubscription(byName);
            visitorDTO.setCoach(noneCoach);
            visitorService.saveVisitor(visitorDTO);
            LOGGER.info("Visitor created {}", visitorDTO.getName());
            modelAndView.addObject("visitor", visitorDTO);
            modelAndView.setViewName("redirect:/visitors");
            return modelAndView;
        } else {
            modelAndView.setViewName("redirect:/visitors/create");
            LOGGER.info("Fail create visitor");
            return modelAndView;
        }
    }


    @DeleteMapping("/{id}")
    public ModelAndView deleteById(@PathVariable String id, ModelAndView modelAndView) {
        Visitor visitor = visitorService.findById(id);
        visitor.setSubscription(null);
        visitor.setCoach(null);
        visitorService.deleteById(id);
        modelAndView.setViewName("redirect:/visitors");
        LOGGER.info("Visitor deleted {}", visitor.getName());
        return modelAndView;
    }

    @PutMapping("/update")
    public ModelAndView update(@Valid Visitor visitor,
                               BindingResult bindingResult,
                               ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/visitors");
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        visitorService.updateVisitor(visitor);
        LOGGER.info("Visitor updated {}", visitor.getName());
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateVisitor(@PathVariable String id, ModelAndView modelAndView) {
        Visitor visitor = visitorService.findById(id);
        Iterable<Coach> coaches = coachService.getAll();
        Iterable<Subscription> subscriptions = subscriptionService.getAll();
        modelAndView.addObject("coaches", coaches);
        modelAndView.addObject("visitor", visitor);
        modelAndView.addObject("subscriptions", subscriptions);
        modelAndView.setViewName("update-visitor");
        return modelAndView;
    }

}
