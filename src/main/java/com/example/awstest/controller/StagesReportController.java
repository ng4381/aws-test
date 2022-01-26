package com.example.awstest.controller;

import com.example.awstest.DAO.AssemblyOrderRemains;
import com.example.awstest.domain.AssemblyOrder;
import com.example.awstest.service.AssemblyOrderDetailStageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@Slf4j
public class StagesReportController {

    private AssemblyOrderDetailStageService assemblyOrderDetailStageService;

    public StagesReportController(AssemblyOrderDetailStageService assemblyOrderDetailStageService) {
        this.assemblyOrderDetailStageService = assemblyOrderDetailStageService;
    }

    @GetMapping("/web/reports/stages/remains")
    public String get(Model model) {
        List<AssemblyOrderRemains> assemblyOrderRemains = assemblyOrderDetailStageService.getAllRemains();
        model.addAttribute("orders_stages_remains", assemblyOrderRemains);
        return "report1/view";
    }
}
