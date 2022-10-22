package com.alevel.gym.controller;

import com.alevel.gym.dto.VisitorDTO;
import com.alevel.gym.model.Sex;
import com.alevel.gym.model.Visitor;
import com.alevel.gym.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAuthority('OWNER')")
@RequestMapping("/admins")
public class AdminsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminsController.class);
    VisitorService visitorService;

    public AdminsController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping
    public ModelAndView getSignUpForm(ModelAndView modelAndView) {
        Iterable<Visitor> all = visitorService.findAllByStatusPeopleAdmins();
        modelAndView.addObject("admins", all);
        modelAndView.setViewName("admins");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView getSignUp(ModelAndView modelAndView) {
        Visitor visitor = new Visitor();
        modelAndView.addObject("admin", visitor);
        modelAndView.addObject("sexes", List.of(Sex.MAN.name(), Sex.WOMAN.name()));
        modelAndView.setViewName("create-admin");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView registrationAdmin(@ModelAttribute VisitorDTO visitorDTO, ModelAndView modelAndView) {
        System.out.println(visitorDTO);
        visitorService.saveAdmin(visitorDTO);
        modelAndView.addObject("admin", visitorDTO);
        modelAndView.setViewName("redirect:/admins");
        LOGGER.info("Admin created {}", visitorDTO.getName());
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteById(@PathVariable String id, ModelAndView modelAndView) {
        visitorService.deleteById(id);
        modelAndView.setViewName("redirect:/admins");
        LOGGER.info("Admin: " + id + " deleted");
        return modelAndView;
    }

    @PutMapping("/update")
    public ModelAndView update(@Valid Visitor visitor, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/admins");
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        visitorService.updateAdmin(visitor);
        LOGGER.info("Admin updated {}", visitor.getName());
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateAdmin(@PathVariable String id, ModelAndView modelAndView) {
        Visitor visitor = visitorService.findById(id);
        modelAndView.addObject("admin", visitor);
        modelAndView.setViewName("update-admin");
        return modelAndView;
    }
}
