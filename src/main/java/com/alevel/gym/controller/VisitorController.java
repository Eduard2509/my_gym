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
        if (visitor.getLockedRoom() != null) {
            LockedRoom room = visitor.getLockedRoom();
            room.setCondition(Condition.ON);
            room.setVisitor(null);
            lockedRoomService.save(room);
            visitor.setLockedRoom(null);
        }
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
        LockedRoom lockedRoomActiveMan = lockedRoomService.findByValueManLocked(numberLocked, Condition.ON.name());
        LockedRoom lockedRoomActiveWoman = lockedRoomService.findByValueWomanLocked(numberLocked, Condition.ON.name());
        Visitor visitor = visitorService.findById(idVisitor);

        if (visitor.getSex().name().equals("MAN") && lockedRoomActiveMan != null && visitor.getLockedRoom() == null) {
            visitor.setLockedRoom(lockedRoomActiveMan);
            lockedRoomActiveMan.setVisitor(visitor);
            lockedRoomActiveMan.setCondition(Condition.OFF);
            visitorService.updateVisitor(visitor);
            lockedRoomService.save(lockedRoomActiveMan);
        }
        if (visitor.getSex().name().equals("WOMAN") && lockedRoomActiveWoman != null && visitor.getLockedRoom() == null) {
            visitor.setLockedRoom(lockedRoomActiveWoman);
            lockedRoomActiveWoman.setVisitor(visitor);
            lockedRoomActiveWoman.setCondition(Condition.OFF);
            visitorService.updateVisitor(visitor);
            lockedRoomService.save(lockedRoomActiveWoman);
        }
        modelAndView.setViewName("redirect:/visitors");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getView(@PathVariable String id, ModelAndView modelAndView) {
        Visitor visitor = visitorService.findById(id);
        modelAndView.addObject("visitor", visitor);
        modelAndView.addObject("id", id);
        modelAndView.setViewName("keys");
        return modelAndView;
    }

    @PatchMapping
    public ModelAndView unlockingKey(@RequestParam String id, ModelAndView modelAndView) {
        Visitor visitor = visitorService.findById(id);
        LockedRoom lockedRoom = visitor.getLockedRoom();
        lockedRoom.setCondition(Condition.ON);
        lockedRoom.setVisitor(null);
        visitor.setLockedRoom(null);
        visitorService.saveVisitor(visitor);
        lockedRoomService.save(lockedRoom);
        modelAndView.setViewName("redirect:/visitors");
        return modelAndView;
    }
}
