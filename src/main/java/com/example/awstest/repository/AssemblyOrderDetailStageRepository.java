package com.example.awstest.repository;

import com.example.awstest.dto.AssemblyOrderRemains;
import com.example.awstest.domain.AssemblyOrderDetailStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssemblyOrderDetailStageRepository extends JpaRepository<AssemblyOrderDetailStage, Long> {
    @Query(nativeQuery = true, value = "select o.id as orderId, o.ext_order_id as extOrderId, od.id as orderDetailId, od.product_id as productId, p.name as productName, SUM(case when ods.pf='p' then ods.qty else -ods.qty end) as qty, 0 as qtyDone, s.id as operationId, s.name as operationName from assembly_order_detail_stage ods " +
            "left join stage s on  ods.stage_id = s.id " +
            "left join assembly_order_detail od on ods.assembly_order_detail_id = od.id " +
            "left join assembly_order o on o.id = od.assembly_order_id " +
            "left join product p on od.product_id = p.id " +
            "group by od.assembly_order_id, od.product_id, s.id")
    List<AssemblyOrderRemains> getAllAssemblyOrderRemains();

    List<AssemblyOrderDetailStage> findByAssemblyOrderDetailId(Long id);
}
