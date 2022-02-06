package com.example.awstest.controller;

import com.example.awstest.domain.AssemblyOrder;
import com.example.awstest.service.AssemblyOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderListController {

    private AssemblyOrderService assemblyOrderService;

    public OrderListController(AssemblyOrderService assemblyOrderService) {
        this.assemblyOrderService = assemblyOrderService;
    }

    @GetMapping("web/orders")
    public String getOrders(Model model) {
        List<AssemblyOrder> assemblyOrders = assemblyOrderService.getAllAssemblyOrders();
        model.addAttribute("orders", assemblyOrders);
        return "order/list";
    }

    @PostMapping("/web/orders/create")
    public String createOrder() {
        AssemblyOrder assemblyOrder = assemblyOrderService.createNewOrder();
        return "redirect:/web/orders/get/" + assemblyOrder.getId();
    }
}
