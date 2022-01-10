package com.example.awstest.service;

import com.example.awstest.DAO.AssemblyOrderRemains;
import com.example.awstest.DAO.TestDTO;
import com.example.awstest.domain.AssemblyOrderDetailStage;
import com.example.awstest.domain.Stage;
import com.example.awstest.repository.AssemblyOrderDetailStageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssemblyOrderDetailStageService {
    private AssemblyOrderDetailStageRepository assemblyOrderDetailStageRepository;
    private StageService stageService;

    public AssemblyOrderDetailStageService(AssemblyOrderDetailStageRepository assemblyOrderDetailStageRepository, StageService stageService) {
        this.assemblyOrderDetailStageRepository = assemblyOrderDetailStageRepository;
        this.stageService = stageService;
    }

    public List<AssemblyOrderRemains> getAllRemains() {
        return assemblyOrderDetailStageRepository.getAllAssemblyOrderRemains();
    }

    public List<TestDTO> getTestDTO() {
        return assemblyOrderDetailStageRepository.gettestDTO();
    }

    public void createAssemblyOrderDetailStage(AssemblyOrderDetailStage orderDetailStage) {
        assemblyOrderDetailStageRepository.save(orderDetailStage);
    }
}
