package com.alevel.gym.controller;

import com.alevel.gym.dto.VisitorDTO;
import com.alevel.gym.mapper.VisitorMapper;
import com.alevel.gym.model.Sex;
import com.alevel.gym.model.Visitor;
import com.alevel.gym.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/visitors")
public class VisitorController {

    VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping
    public ModelAndView getSignUpForm(ModelAndView modelAndView) {
        Iterable<Visitor> all = visitorService.getAll();
        modelAndView.addObject("visitors", all);
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
        System.out.println(visitorDTO);
        visitorService.save(visitorDTO);
        modelAndView.addObject("visitor", visitorDTO);
        modelAndView.setViewName("redirect:/visitors");
        return modelAndView;
    }


    @DeleteMapping("/{id}")
    public ModelAndView deleteById(@PathVariable String id, ModelAndView modelAndView) {
        visitorService.deleteById(id);
        modelAndView.setViewName("redirect:/visitors");
        return modelAndView;
    }

    @PutMapping("/update")
    public ModelAndView update(@Valid Visitor visitor, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/visitors");
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        visitorService.update(visitor);
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateVisitor(@PathVariable String id, ModelAndView modelAndView) {
        Visitor visitor = visitorService.findById(id);
        modelAndView.addObject("visitor", visitor);
        modelAndView.setViewName("update-visitor");
        return modelAndView;
    }
}
