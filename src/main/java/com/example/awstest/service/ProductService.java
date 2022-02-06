package com.example.awstest.service;

import com.example.awstest.domain.AssemblyOrderDetail;
import com.example.awstest.domain.Product;
import com.example.awstest.exception.ProductCanNotBeDeletedException;
import com.example.awstest.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private AssemblyOrderDetailService assemblyOrderDetailService;

    public ProductService(ProductRepository productRepository, AssemblyOrderDetailService assemblyOrderDetailService) {
        this.productRepository = productRepository;
        this.assemblyOrderDetailService = assemblyOrderDetailService;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.getById(id);
    }

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {

        List<AssemblyOrderDetail> assemblyOrderDetails = assemblyOrderDetailService.getAssemblyOrderDetailsByProductId(id);
        String assemblyOrders = assemblyOrderDetails.stream()
                .map(assemblyOrderDetail -> {
                    if (assemblyOrderDetail.getAssemblyOrder() != null) {
                        return assemblyOrderDetail.getAssemblyOrder().getId().toString();
                    }
                    return "";
                })
                .collect(Collectors.joining("; "));

        if (!assemblyOrderDetails.isEmpty()) {
            throw new ProductCanNotBeDeletedException("Product (" + id + ") can't be deleted. It used in assembly orders: " + assemblyOrders);
        }

        productRepository.deleteById(id);
    }
}
