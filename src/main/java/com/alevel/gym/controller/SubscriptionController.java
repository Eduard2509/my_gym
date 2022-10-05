package com.alevel.gym.controller;

import com.alevel.gym.dto.SubscriptionDTO;
import com.alevel.gym.model.Subscription;
import com.alevel.gym.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {

    SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public Iterable<Subscription> getAll() {
        return subscriptionService.getAll();
    }

    @GetMapping("/find-by-id")
    public SubscriptionDTO findById(@RequestParam String id){
        return subscriptionService.findById(id);
    }

    @GetMapping("/find-by-name")
    public Optional<Subscription> findByName(@RequestBody String name){
        return subscriptionService.findByName(name);
    }

    @GetMapping("/create")
    public String createDefaultCoach() {
        return subscriptionService.createDefaultSubscription();
    }

    @PutMapping("/update/{id}")
    public SubscriptionDTO update(@PathVariable String id, @RequestBody SubscriptionDTO subscriptionDTO) {
        return subscriptionService.update(id, subscriptionDTO);
    }

    @DeleteMapping
    public void deleteById(@RequestParam String id) {
        subscriptionService.deleteById(id);
    }
}
