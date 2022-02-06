package com.example.awstest.repository;

import com.example.awstest.domain.AssemblyOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssemblyOrderDetailRepository extends JpaRepository<AssemblyOrderDetail, Long> {
    List<AssemblyOrderDetail> findByAssemblyOrderId(Long id);
}
