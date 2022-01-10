package com.example.awstest.service;

import com.example.awstest.domain.AssemblyOrderDetail;
import com.example.awstest.repository.AssemblyOrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssemblyOrderDetailService {
    private AssemblyOrderDetailRepository assemblyOrderDetailRepository;

    public AssemblyOrderDetailService(AssemblyOrderDetailRepository assemblyOrderDetailRepository) {
        this.assemblyOrderDetailRepository = assemblyOrderDetailRepository;
    }

    public AssemblyOrderDetail createAssemblyOrderDetail(AssemblyOrderDetail assemblyOrderDetail) {
        return assemblyOrderDetailRepository.save(assemblyOrderDetail);
    }

    public AssemblyOrderDetail getAssemblyOrderDetailById(Long id) {
        Optional<AssemblyOrderDetail> assemblyOrderDetailOption = assemblyOrderDetailRepository.findById(id);
        if(assemblyOrderDetailOption.isEmpty()) {
            throw new RuntimeException("Order details not found");
        }
        return assemblyOrderDetailOption.get();
    }
}
