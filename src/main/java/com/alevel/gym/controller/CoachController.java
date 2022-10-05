package com.alevel.gym.controller;

import com.alevel.gym.dto.CoachDTO;
import com.alevel.gym.model.Coach;
import com.alevel.gym.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/coach")
public class CoachController {
    CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping()
    public Iterable<Coach> getAll() {
        return coachService.getAll();
    }

    @GetMapping("/find-by-id")
    public CoachDTO findById(@RequestParam String id){
        return coachService.findById(id);
    }

    @GetMapping("/find-by-name")
    public Optional<Coach> findByName(@RequestBody String name){
        return coachService.findByName(name);
    }

    @GetMapping("/create")
    public String createDefaultCoach() {
        return coachService.createDefaultCoach();
    }

    @PutMapping("/update/{id}")
    public CoachDTO update(@PathVariable String id, @RequestBody CoachDTO coachDTO) {
        return coachService.update(id, coachDTO);
    }

    @DeleteMapping
    public void deleteById(@RequestParam String id) {
        coachService.deleteById(id);
    }
}
