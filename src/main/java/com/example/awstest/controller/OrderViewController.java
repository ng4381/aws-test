package com.example.awstest.controller;

import com.example.awstest.dto.AssemblyOrderFormDTO;
import com.example.awstest.domain.AssemblyOrder;
import com.example.awstest.domain.AssemblyOrderDetail;
import com.example.awstest.domain.Product;
import com.example.awstest.service.AssemblyOrderDetailService;
import com.example.awstest.service.AssemblyOrderService;
import com.example.awstest.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.awstest.dto.AssemblyOrderFormDTO.*;


@Controller
@SessionAttributes({"order", "products"})
@Slf4j
public class OrderViewController {

    private AssemblyOrderService assemblyOrderService;
    private AssemblyOrderDetailService assemblyOrderDetailService;
    private ProductService productService;


    public OrderViewController(AssemblyOrderService assemblyOrderService, AssemblyOrderDetailService assemblyOrderDetailService, AssemblyOrder assemblyOrder, ProductService productService) {
        this.assemblyOrderService = assemblyOrderService;
        this.assemblyOrderDetailService = assemblyOrderDetailService;
        this.productService = productService;
    }

    @GetMapping("web/orders/get/{id}")
    public String viewStart(@PathVariable Long id, Model model){
        AssemblyOrder assemblyOrder = assemblyOrderService.getOrderById(id);
        List<AssemblyOrderDetail> assemblyOrderDetails = assemblyOrderDetailService.getAssemblyOrderDetailsByOrderId(assemblyOrder.getId());

        AssemblyOrderFormDTO assemblyOrderForm = assemblyOrderService.buildAssemblyOrderFormDTO(assemblyOrder);

        model.addAttribute("order", assemblyOrderForm);
        return "redirect:/web/orders/get/test";
    }

    @GetMapping("web/orders/get/test")
    public String view(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "order/view";
    }

    @PostMapping("web/orders/post")
    public String OrderSubmit(@ModelAttribute AssemblyOrderDetail assemblyOrder, Model model) {
        model.addAttribute("od", assemblyOrder);
        return "order/list";
    }

    @PostMapping("/ordermng")
    public String addRow(@ModelAttribute("order") AssemblyOrderFormDTO assemblyOrderForm, @RequestParam(value = "action", required = true) String action){
        if (action.equals("addRow")) {
            AssemblyOrderDetailDTO assemblyOrderDetailDTO = new AssemblyOrderDetailDTO();
            assemblyOrderDetailDTO.setQty(1);
            assemblyOrderDetailDTO.setProduct(new Product());
            assemblyOrderDetailDTO.setNew(true);

            assemblyOrderForm.getAssemblyOrderDetails().add(assemblyOrderDetailDTO);
        } else if (action.equals("submit")){

            assemblyOrderService.updateOrder(assemblyOrderForm);
            log.info("*NIK* Order saved. " + assemblyOrderForm.getAssemblyOrderDetails());
            return "redirect:/web/orders";
        }
        return "redirect:/web/orders/get/test";
    }
}