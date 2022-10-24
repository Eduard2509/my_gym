package com.alevel.gym.controller;

import com.alevel.gym.dto.VisitorDTO;
import com.alevel.gym.model.*;
import com.alevel.gym.service.CoachService;
import com.alevel.gym.service.LockedRoomService;
import com.alevel.gym.service.SubscriptionService;
import com.alevel.gym.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
@RequestMapping("/visitors")
public class VisitorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisitorController.class);

    VisitorService visitorService;
    SubscriptionService subscriptionService;
    CoachService coachService;
    LockedRoomService lockedRoomService;

    @Autowired
    public VisitorController(VisitorService visitorService, SubscriptionService subscriptionService,
                             CoachService coachService, LockedRoomService lockedRoomService) {
        this.visitorService = visitorService;
        this.subscriptionService = subscriptionService;
        this.coachService = coachService;
        this.lockedRoomService = lockedRoomService;
    }

    @GetMapping
    public ModelAndView getAllAdmins(ModelAndView modelAndView) {
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

    @GetMapping("/locked/{id}")
    public ModelAndView getAllLocked(@PathVariable String id, ModelAndView modelAndView) {
        Visitor visitor = visitorService.findById(id);
        modelAndView.addObject("visitorId", visitor.getId());
        if (visitor.getSex().name().equals("MAN")) {
            List<LockedRoom> allLockedForMan = lockedRoomService.findAllLockedForMan();
            modelAndView.addObject("allLockedFor", allLockedForMan);
            modelAndView.setViewName("locked");
        }
        if (visitor.getSex().name().equals("WOMAN")) {
            List<LockedRoom> allLockedForWoman = lockedRoomService.findAllLockedForWoman();
            modelAndView.addObject("allLockedFor", allLockedForWoman);
            modelAndView.setViewName("locked");
        }
        return modelAndView;
    }

    @PatchMapping("/locked")
    public ModelAndView updateLocked(@RequestParam String idVisitor,
                                     @RequestParam int numberLocked,
                                     ModelAndView modelAndView) {
        LockedRoom lockedRoomActive = lockedRoomService.findByValue(numberLocked, Condition.ON.name());
        LockedRoom lockedRoomDisabled = lockedRoomService.findByValue(numberLocked, Condition.OFF.name());
        Visitor visitor = visitorService.findById(idVisitor);
       if (lockedRoomActive.getSex() != null && lockedRoomService.findByVisitorId(idVisitor)) {
           lockedRoomActive.setVisitor(visitor);
           lockedRoomActive.setCondition(Condition.OFF);
           lockedRoomDisabled.setCondition(Condition.ON);
           lockedRoomService.save(lockedRoomActive);
           lockedRoomService.save(lockedRoomDisabled);
       }
       if (lockedRoomActive.getSex() == null && !lockedRoomService.findByVisitorId(idVisitor)) {
           lockedRoomDisabled.setVisitor(null);
           lockedRoomDisabled.setCondition(Condition.ON);
           lockedRoomActive.setCondition(Condition.OFF);
           lockedRoomService.save(lockedRoomDisabled);
           lockedRoomService.save(lockedRoomActive);
       }
       modelAndView.setViewName("redirect:/visitors");
        return modelAndView;
    }
}
