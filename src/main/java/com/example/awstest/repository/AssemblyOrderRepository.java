package com.example.awstest.repository;

import com.example.awstest.domain.AssemblyOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssemblyOrderRepository extends JpaRepository<AssemblyOrder, Long> {
}
