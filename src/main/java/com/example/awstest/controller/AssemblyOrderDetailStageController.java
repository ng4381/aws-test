package com.example.awstest.controller;

import com.example.awstest.DAO.AssemblyOrderRemains;
import com.example.awstest.DAO.TestDTO;
import com.example.awstest.service.AssemblyOrderDetailStageService;
import org.springframework.web.bind.annotation.GetMapping;
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


    /*
    @GetMapping("/orders/stages/remains")
    public List<TestDTO> get() {
        return assemblyOrderDetailStageService.getTestDTO();
    }
     */

}
