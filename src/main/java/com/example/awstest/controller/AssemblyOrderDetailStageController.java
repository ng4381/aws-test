package com.example.awstest.controller;

import com.example.awstest.DAO.AssemblyOrderRemains;
import com.example.awstest.DAO.AssemblyOrderRemainsDTO;
import com.example.awstest.domain.AssemblyOrderDetailStage;
import com.example.awstest.service.AssemblyOrderDetailStageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssemblyOrderDetailStageController {

    private AssemblyOrderDetailStageService assemblyOrderDetailStageService;

    public AssemblyOrderDetailStageController(AssemblyOrderDetailStageService assemblyOrderDetailStageService) {
        this.assemblyOrderDetailStageService = assemblyOrderDetailStageService;
    }

    @GetMapping("/orders/stages/remains")
    public List<AssemblyOrderRemains> get() {
        return assemblyOrderDetailStageService.getAllRemains();
    }


    @PostMapping("/orders/stages/remains")
    public void createAssemblyOrderDetailStage(@RequestBody List<AssemblyOrderRemainsDTO> assemblyOrderRemains) {
        assemblyOrderDetailStageService.createAssemblyOrderDetailStageFact(assemblyOrderRemains);
    }


    @PostMapping("/orders/stages")
    public void createAssemblyOrderDetailStage(@RequestBody AssemblyOrderDetailStage assemblyOrderDetailStage) {
        assemblyOrderDetailStageService.createAssemblyOrderDetailStage(assemblyOrderDetailStage);
    }

    /*
    @GetMapping("/orders/stages/remains")
    public List<TestDTO> get() {
        return assemblyOrderDetailStageService.getTestDTO();
    }
     */

}
