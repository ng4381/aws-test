package com.example.awstest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {
    @GetMapping("/web/home")
    public String viewHome() {
        return "home/view";
    }

    @GetMapping("/web/error")
    public String viewError() {
        return "error/view";
    }
}
