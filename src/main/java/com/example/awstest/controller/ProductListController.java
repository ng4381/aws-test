package com.example.awstest.controller;

import com.example.awstest.domain.Product;
import com.example.awstest.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class ProductListController {

    private ProductService productService;

    public ProductListController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("web/products")
    public String getProducts(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("products", productService.getAllProducts());
        return "product/list";
    }

    @PostMapping("web/products/create")
    public String addRow(@ModelAttribute("product") Product product, @RequestParam(value = "action", required = true) String action){
        if (action.equals("create")) {
            productService.createProduct(product);
            log.info("**NIK** Creating product ...");
        }
        return "redirect:/web/products";
    }

    @GetMapping("web/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        log.info("**Nik** Deleting product(" + id + ")");
        productService.deleteProduct(id);
        return "redirect:/web/products";
    }

    @RequestMapping(value = "/web/products/delete", method = RequestMethod.POST)
    private String deleteStudent(@RequestParam Long id){
        System.out.println("product id : "+id);
        return "redirect:/web/products";
    }
}
