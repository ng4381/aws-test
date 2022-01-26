package com.example.awstest.controller;

import com.example.awstest.domain.AssemblyOrder;
import com.example.awstest.domain.AssemblyOrderDetail;
import com.example.awstest.service.AssemblyOrderDetailService;
import com.example.awstest.service.AssemblyOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("order")
@Slf4j
public class OrderViewControll {

    private AssemblyOrderService assemblyOrderService;
    private AssemblyOrderDetailService assemblyOrderDetailService;

    private AssemblyOrder assemblyOrder;

    public OrderViewControll(AssemblyOrderService assemblyOrderService, AssemblyOrderDetailService assemblyOrderDetailService, AssemblyOrder assemblyOrder) {
        this.assemblyOrderService = assemblyOrderService;
        this.assemblyOrderDetailService = assemblyOrderDetailService;
    }

    @GetMapping("web/orders/get/{id}")
    public String view(@PathVariable Long id, Model model){

        if(assemblyOrder==null) {
            assemblyOrder = assemblyOrderService.getOrderById(id);
            log.info("**NIK** View.Creating new assemblyOrder bean");
        } else {
            if(assemblyOrder.getId()!=id){
                assemblyOrder = assemblyOrderService.getOrderById(id);
            }
            log.info("**NIK** View.AssemblyOrder bean is NOT NULL");
        }
        model.addAttribute("order", assemblyOrder);
        log.info("**NIK** View.AssemblyOrder = " + assemblyOrder);
        return "order/view";
    }

    @PostMapping("web/orders/get/test")
    public String testOrder() {
        return "order/view";
    }


    @PostMapping("web/orders/post")
    public String OrderSubmit(@ModelAttribute AssemblyOrderDetail assemblyOrder, Model model) {
        model.addAttribute("od", assemblyOrder);
        return "order/list";
    }


    @PostMapping("/ordermng")
    public String addRow(@ModelAttribute("order") AssemblyOrder assemblyOrder, @RequestParam(value = "action", required = true) String action){
        if (action.equals("addRow")) {
            log.info("**NIK** addRow.assemblyOrder = " + assemblyOrder);
            assemblyOrder.getAssemblyOrderDetails().add(new AssemblyOrderDetail());
        } else {
            log.info("**NIK** SAVING ORDER");
            assemblyOrderService.updateOrder(assemblyOrder);
            assemblyOrder = null;
            return "redirect:/web/orders";
        }
        return "redirect:/web/orders/get/" + assemblyOrder.getId();
    }


}
