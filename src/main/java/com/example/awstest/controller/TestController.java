package com.example.awstest.controller;

import com.example.awstest.domain.AssemblyOrderDetail;
import com.example.awstest.domain.AssemblyOrderDetailStage;
import com.example.awstest.service.AssemblyOrderDetailService;
import com.example.awstest.service.AssemblyOrderDetailStageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private AssemblyOrderDetailService assemblyOrderDetailService;
    private AssemblyOrderDetailStageService assemblyOrderDetailStageService;

    public TestController(AssemblyOrderDetailService assemblyOrderDetailService, AssemblyOrderDetailStageService assemblyOrderDetailStageService) {
        this.assemblyOrderDetailService = assemblyOrderDetailService;
        this.assemblyOrderDetailStageService = assemblyOrderDetailStageService;
    }

    @GetMapping
    public String getData() {
        return "Hello from NIKITA";
    }

    @GetMapping(value = "/test/od/{id}")
    public List<AssemblyOrderDetail> getAssemblyOrderDetail(@PathVariable Long id) {
        return assemblyOrderDetailService.getAssemblyOrderDetailsByOrderId(id);
    }

    @GetMapping(value = "/test/ods/{id}")
    public List<AssemblyOrderDetailStage> getAssemblyOrderDetailStage(@PathVariable Long id) {
        return assemblyOrderDetailStageService.findByAssemblyOrderDetailId(id);
    }
}
