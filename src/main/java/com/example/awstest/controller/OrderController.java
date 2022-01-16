package com.example.awstest.controller;

import com.example.awstest.domain.AssemblyOrder;
import com.example.awstest.domain.AssemblyOrderDetail;
import com.example.awstest.service.AssemblyOrderService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OrderController {
    private AssemblyOrderService assemblyOrderService;

    public OrderController(AssemblyOrderService assemblyOrderService) {
        this.assemblyOrderService = assemblyOrderService;
    }

    @GetMapping("/orders")
    public List<AssemblyOrder> getOrders() {
        return assemblyOrderService.getAllAssemblyOrders();
    }

    @PostMapping(value = "/orders")
    public void createOrder(@RequestBody AssemblyOrder assemblyOrder) {
        assemblyOrderService.createOrder(assemblyOrder);
    }





}
