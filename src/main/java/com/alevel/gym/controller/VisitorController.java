package com.alevel.gym.controller;

import com.alevel.gym.dto.VisitorDTO;
import com.alevel.gym.mapper.VisitorMapper;
import com.alevel.gym.model.Visitor;
import com.alevel.gym.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/visitor")
public class VisitorController {

    VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

//    @GetMapping("/sign-up")
//    public ModelAndView getSignUpForm(ModelAndView modelAndView) {
//        Visitor visitor = new Visitor();
//        modelAndView.addObject("visitor", visitor);
//        modelAndView.setViewName("sign-up");
//        return modelAndView;
//    }
//
//    @PostMapping
//    public ModelAndView createVisitor(@ModelAttribute VisitorDTO visitorDTO, ModelAndView modelAndView) {
//        System.out.println(visitorDTO);
//        modelAndView.addObject("visitor", visitorDTO);
//        modelAndView.setViewName("sign-up");
//        Visitor visitor = VisitorMapper.mapFromDTO(visitorDTO);
//        visitorService.save(visitor);
//        return modelAndView;
//    }
}
