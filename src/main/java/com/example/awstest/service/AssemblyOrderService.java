package com.example.awstest.service;

import com.example.awstest.domain.AssemblyOrder;
import com.example.awstest.domain.AssemblyOrderDetail;
import com.example.awstest.domain.AssemblyOrderDetailStage;
import com.example.awstest.domain.Stage;
import com.example.awstest.repository.AssemblyOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssemblyOrderService {
    private AssemblyOrderRepository assemblyOrderRepository;
    private AssemblyOrderDetailStageService assemblyOrderDetailStageService;
    private AssemblyOrderDetailService assemblyOrderDetailService;
    private StageService stageService;

    public AssemblyOrderService(AssemblyOrderRepository assemblyOrderRepository, AssemblyOrderDetailStageService assemblyOrderDetailStageService, AssemblyOrderDetailService assemblyOrderDetailService, StageService stageService) {
        this.assemblyOrderRepository = assemblyOrderRepository;
        this.assemblyOrderDetailStageService = assemblyOrderDetailStageService;
        this.assemblyOrderDetailService = assemblyOrderDetailService;
        this.stageService = stageService;
    }

    public List<AssemblyOrder> getAllAssemblyOrders() {
        return assemblyOrderRepository.findAll();
    }

    public void createOrder(AssemblyOrder assemblyOrder) {

        Stage stage = null;
        AssemblyOrder order = assemblyOrderRepository.save(assemblyOrder);
        for(AssemblyOrderDetail orderDetail : assemblyOrder.getAssemblyOrderDetails()) {

            orderDetail.setAssemblyOrder(order);
            AssemblyOrderDetail assemblyOrderDetail = assemblyOrderDetailService.createAssemblyOrderDetail(orderDetail);

            for(AssemblyOrderDetailStage orderDetailStage : orderDetail.getAssemblyOrderDetailStages()) {
                orderDetailStage.setAssemblyOrderDetail(assemblyOrderDetail);
                stage = stageService.getStageById(orderDetailStage.getStage().getId());
                orderDetailStage.setStage(stage);
                assemblyOrderDetailStageService.createAssemblyOrderDetailStage(orderDetailStage);
            }
        }

    }
}
