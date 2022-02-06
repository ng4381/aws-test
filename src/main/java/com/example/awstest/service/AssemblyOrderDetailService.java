package com.example.awstest.service;

import com.example.awstest.domain.AssemblyOrder;
import com.example.awstest.domain.AssemblyOrderDetail;
import com.example.awstest.exception.AssemblyOrderDetailNotFoundException;
import com.example.awstest.exception.AssemblyOrderNotFoundException;
import com.example.awstest.repository.AssemblyOrderDetailRepository;
import com.example.awstest.repository.AssemblyOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssemblyOrderDetailService {
    private AssemblyOrderDetailRepository assemblyOrderDetailRepository;
    private AssemblyOrderRepository assemblyOrderRepository;

    public AssemblyOrderDetailService(AssemblyOrderDetailRepository assemblyOrderDetailRepository, AssemblyOrderRepository assemblyOrderRepository) {
        this.assemblyOrderDetailRepository = assemblyOrderDetailRepository;
        this.assemblyOrderRepository = assemblyOrderRepository;
    }

    public void createAssemblyOrderDetail(AssemblyOrderDetail assemblyOrderDetail) {
        assemblyOrderDetailRepository.save(assemblyOrderDetail);
    }

    public void updateAssemblyOrderDetail(AssemblyOrderDetail assemblyOrderDetail) {
        assemblyOrderDetailRepository.save(assemblyOrderDetail);
    }

    public AssemblyOrderDetail getAssemblyOrderDetailById(Long id) {
        Optional<AssemblyOrderDetail> assemblyOrderDetailOption = assemblyOrderDetailRepository.findById(id);
        if (assemblyOrderDetailOption.isEmpty()) {
            throw new AssemblyOrderDetailNotFoundException("Assembly order detail(" + id + ") not found!");
        }
        return assemblyOrderDetailOption.get();
    }

    public List<AssemblyOrderDetail> getAssemblyOrderDetailsByOrderId(Long id) {
        Optional<AssemblyOrder> assemblyOrderOption = assemblyOrderRepository.findById(id);
        if (assemblyOrderOption.isEmpty()) {
            throw new AssemblyOrderNotFoundException("Assembly order(" + id + ") not found!");
        }
        return assemblyOrderDetailRepository.findByAssemblyOrderId(id);
    }

    public List<AssemblyOrderDetail> getAssemblyOrderDetailsByProductId(Long id) {
        return assemblyOrderDetailRepository.findByProductId(id);
    }
}
