package com.alevel.gym.controller;

import com.alevel.gym.dto.CoachDTO;
import com.alevel.gym.model.Coach;
import com.alevel.gym.model.Sex;
import com.alevel.gym.service.CoachService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/coaches")
public class CoachController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoachController.class);
    CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping
    public ModelAndView getAllCoaches(@RequestParam(required = false) String value, ModelAndView modelAndView) {
        if (value == null) {
            Iterable<Coach> coaches = coachService.getAll();
            modelAndView.addObject("coaches", coaches);
        } else {
            List<Coach> findCoach = coachService.findByNameOrSurname(value);
            modelAndView.addObject("coaches", findCoach);
        }
        modelAndView.setViewName("coaches");
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteById(@PathVariable String id, ModelAndView modelAndView) {
        coachService.deleteById(id);
        LOGGER.info("Coach " + id + " deleted successfully");
        modelAndView.setViewName("redirect:/coaches");
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView getSignUp(ModelAndView modelAndView) {
        Coach coach = new Coach();
        modelAndView.addObject("coach", coach);
        modelAndView.addObject("sexes", List.of(Sex.MAN.name(), Sex.WOMAN.name()));
        modelAndView.setViewName("create-coach");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView registrationCoach(@ModelAttribute CoachDTO coachDTO, ModelAndView modelAndView) {
        System.out.println(coachDTO);
        coachService.save(coachDTO);
        LOGGER.info("Coach created {}", coachDTO.getName());
        modelAndView.addObject("coach", coachDTO);
        modelAndView.setViewName("redirect:/coaches");
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateCoach(@PathVariable String id, ModelAndView modelAndView) {
        Coach coach = coachService.findById(id);
        modelAndView.addObject("coach", coach);
        modelAndView.setViewName("update-coach");
        return modelAndView;
    }

    @PutMapping("/update")
    public ModelAndView update(@Valid Coach coach, BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/coaches");
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        coachService.update(coach);
        LOGGER.info("Visitor updated {}", coach.getName());
        return modelAndView;
    }

}
