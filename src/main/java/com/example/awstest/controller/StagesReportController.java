package com.example.awstest.controller;

import com.example.awstest.dto.AssemblyOrderRemains;
import com.example.awstest.service.AssemblyOrderDetailStageService;
import com.example.awstest.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class StagesReportController {

    private AssemblyOrderDetailStageService assemblyOrderDetailStageService;
    private ProductService productService;

    public StagesReportController(AssemblyOrderDetailStageService assemblyOrderDetailStageService, ProductService productService) {
        this.assemblyOrderDetailStageService = assemblyOrderDetailStageService;
        this.productService = productService;
    }

    @GetMapping("/web/reports/stages/remains")
    public String get(Model model) {
        List<AssemblyOrderRemains> assemblyOrderRemains = assemblyOrderDetailStageService.getAllRemains();
        model.addAttribute("orders_stages_remains", assemblyOrderRemains);
        return "report1/view";
    }
}
