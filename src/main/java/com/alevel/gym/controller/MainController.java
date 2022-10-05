package com.alevel.gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
    @GetMapping
    public String main() {
        return "My gym application started<br>" +
                "<a href = \"http://localhost:8080/coach\"> Get all coaches</a><br>" +
                "<a href = \"http://localhost:8080/coach/create\"> Create default coach</a><br>" +
                "<a href = \"http://localhost:8080/coach/update\"> Update coach</a><br>" +
                "<a href = \"http://localhost:8080/coach/delete\"> Delete coach</a><br>" +
                "<a href = \"http://localhost:8080/subscription/create\"> Create subscription </a><br>" +
                "<a href = \"http://localhost:8080/subscription\"> Get all subscriptions</a><br>";
    }
}
