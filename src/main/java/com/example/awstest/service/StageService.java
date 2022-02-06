package com.example.awstest.service;

import com.example.awstest.domain.Stage;
import com.example.awstest.repository.StageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {
    private StageRepository stageRepository;

    public StageService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    public Stage getStageById(Long id) {

        Optional<Stage> optionalStage = stageRepository.findById(id);
        if (optionalStage.isEmpty()) {
            throw new RuntimeException("Stage not found!!!");
        }

        return optionalStage.get();
    }

    public void createStage(Stage stage) {
        stageRepository.save(stage);
    }
}
