package com.alevel.gym.controller;

import com.alevel.gym.dto.VisitorDTO;
import com.alevel.gym.mapper.VisitorMapper;
import com.alevel.gym.model.*;
import com.alevel.gym.service.CoachService;
import com.alevel.gym.service.SubscriptionService;
import com.alevel.gym.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
@RequestMapping("/visitors")
public class VisitorController {

    VisitorService visitorService;
    SubscriptionService subscriptionService;
    CoachService coachService;

    @Autowired
    public VisitorController(VisitorService visitorService, SubscriptionService subscriptionService, CoachService coachService) {
        this.visitorService = visitorService;
        this.subscriptionService = subscriptionService;
        this.coachService = coachService;
    }

    @GetMapping("/{pageNumber}")
    public ModelAndView getPageVisitors(@PathVariable(value = "pageNumber") int pageNumber,
                                        @RequestParam("sortField") String sortField,
                                        @RequestParam("sortDirection") String sortDirection,
                                        ModelAndView modelAndView) {
        int pageSize = 10;

        Page<Visitor> page = visitorService.getAllVisitors(pageNumber, pageSize, sortField, sortDirection);
        List<Visitor> visitors = page.getContent();
        modelAndView.addObject("currentPage", pageNumber);
        modelAndView.addObject("totalPages", page.getTotalPages());
        modelAndView.addObject("totalItems", page.getTotalElements());
        modelAndView.addObject("sortField", sortField);
        modelAndView.addObject("sortDirection", sortDirection);
        modelAndView.addObject("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");
        modelAndView.addObject("visitors", visitors);
        modelAndView.setViewName("visitors");
        return modelAndView;
    }

    @GetMapping
    public ModelAndView getVisitors(ModelAndView modelAndView) {
        return getPageVisitors(1, "name", "asc", modelAndView);
    }

//    @GetMapping
//    public ModelAndView getSignUpForm(ModelAndView modelAndView) {
//        Iterable<Visitor> all = visitorService.findAllByStatusPeopleVisitors();
//        modelAndView.addObject("visitors", all);
//        modelAndView.setViewName("visitors");
//        return modelAndView;
//    }

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
        System.out.println(visitorDTO);
        Subscription byName = subscriptionService.findByName(NamesSubscription.NONE);
        Coach noneCoach = coachService.findByName("NONE");
        visitorDTO.setSubscription(byName);
        visitorDTO.setCoach(noneCoach);
        visitorService.saveVisitor(visitorDTO);
        modelAndView.addObject("visitor", visitorDTO);
        modelAndView.setViewName("redirect:/visitors");
        return modelAndView;
    }


    @DeleteMapping("/{id}")
    public ModelAndView deleteById(@PathVariable String id, ModelAndView modelAndView) {
        Visitor visitor = visitorService.findById(id);
        visitor.setSubscription(null);
        visitor.setCoach(null);
        visitorService.deleteById(id);
        modelAndView.setViewName("redirect:/visitors");
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
