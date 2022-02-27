package com.example.awstest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StagesListController {
    @GetMapping("web/stages")
    public String getOrders() {
        return "stages/list";
    }
}
