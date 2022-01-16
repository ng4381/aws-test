package com.example.awstest.service;

import com.example.awstest.domain.AssemblyOrder;
import com.example.awstest.domain.AssemblyOrderDetail;
import com.example.awstest.domain.AssemblyOrderDetailStage;
import com.example.awstest.domain.Stage;
import com.example.awstest.repository.AssemblyOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                assemblyOrderDetailStageService.createAssemblyOrderDetailStage(orderDetailStage);
            }
        }
    }

    public AssemblyOrder getOrderById(Long id) {
        Optional<AssemblyOrder> assemblyOrderOptional = assemblyOrderRepository.findById(id);
        if(assemblyOrderOptional.isEmpty()) {
            throw new RuntimeException("Order not found!");
        };
        return assemblyOrderOptional.get();
    }


    public void updateOrder(AssemblyOrder assemblyOrder) {

        AssemblyOrder order = assemblyOrderRepository.save(assemblyOrder);
        for(AssemblyOrderDetail orderDetail : assemblyOrder.getAssemblyOrderDetails()) {

            orderDetail.setAssemblyOrder(order);
            AssemblyOrderDetail _orderDetail = assemblyOrderDetailService.createAssemblyOrderDetail(orderDetail);

            List<AssemblyOrderDetailStage> orderDetailStages = _orderDetail.getAssemblyOrderDetailStages();

            if(orderDetailStages.isEmpty()) {
                AssemblyOrderDetailStage orderDetailStage1 = new AssemblyOrderDetailStage();
                orderDetailStage1.setStage(stageService.getStageById(1L));
                orderDetailStage1.setQty(orderDetail.getQty());
                orderDetailStage1.setPf("p");
                assemblyOrderDetailStageService.createAssemblyOrderDetailStage(orderDetailStage1);

                AssemblyOrderDetailStage orderDetailStage2 = new AssemblyOrderDetailStage();
                orderDetailStage2.setStage(stageService.getStageById(2L));
                orderDetailStage2.setQty(orderDetail.getQty());
                orderDetailStage2.setPf("p");
                assemblyOrderDetailStageService.createAssemblyOrderDetailStage(orderDetailStage2);
            }
        }
    }
}
