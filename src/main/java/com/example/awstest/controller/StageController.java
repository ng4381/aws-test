package com.example.awstest.controller;

import com.example.awstest.domain.Stage;
import com.example.awstest.service.StageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StageController {
    private StageService stageService;

    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @GetMapping("/stages")
    public List<Stage> getStages() {
        return stageService.getAllStages();
    }

    @PostMapping("/stages")
    public void createStage(@RequestBody Stage stage) {
        stageService.createStage(stage);
    }
}
