package com.example.awstest.service;

import com.example.awstest.domain.AssemblyOrder;
import com.example.awstest.domain.AssemblyOrderDetail;
import com.example.awstest.domain.AssemblyOrderDetailStage;
import com.example.awstest.dto.AssemblyOrderFormDTO;
import com.example.awstest.repository.AssemblyOrderRepository;
import com.example.awstest.util.Constants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.awstest.dto.AssemblyOrderFormDTO.AssemblyOrderDetailDTO;

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
        AssemblyOrder order = assemblyOrderRepository.save(assemblyOrder);
    }

    public AssemblyOrder getOrderById(Long id) {
        Optional<AssemblyOrder> assemblyOrderOptional = assemblyOrderRepository.findById(id);
        if (assemblyOrderOptional.isEmpty()) {
            throw new RuntimeException("Order not found!");
        }
        ;
        return assemblyOrderOptional.get();
    }


    public void updateOrder(AssemblyOrderFormDTO assemblyOrderFormDTO) {

        Long assemblyOrderId = assemblyOrderFormDTO.getId();
        AssemblyOrder assemblyOrder = getOrderById(assemblyOrderId);
        assemblyOrder.setExtOrderId(assemblyOrderFormDTO.getExtOrderId());

        assemblyOrderRepository.save(assemblyOrder);

        for (AssemblyOrderDetailDTO assemblyOrderDetailDTO : assemblyOrderFormDTO.getAssemblyOrderDetails()) {

            AssemblyOrderDetail assemblyOrderDetail;
            if (assemblyOrderDetailDTO.isNew()) {
                assemblyOrderDetail = new AssemblyOrderDetail();
            } else {
                assemblyOrderDetail = assemblyOrderDetailService.getAssemblyOrderDetailById(assemblyOrderDetailDTO.getId());
            }

            assemblyOrderDetail.setAssemblyOrder(assemblyOrder);
            assemblyOrderDetail.setProduct(assemblyOrderDetailDTO.getProduct());
            assemblyOrderDetail.setQty(assemblyOrderDetailDTO.getQty());

            assemblyOrderDetailService.updateAssemblyOrderDetail(assemblyOrderDetail);

            List<AssemblyOrderDetailStage> orderDetailStages = assemblyOrderDetailStageService.findByAssemblyOrderDetailId(assemblyOrderDetail.getId());

            if (orderDetailStages.isEmpty()) {
                AssemblyOrderDetailStage orderDetailStage1 = new AssemblyOrderDetailStage();
                orderDetailStage1.setStage(stageService.getStageById(1L));
                orderDetailStage1.setQty(assemblyOrderDetail.getQty());
                orderDetailStage1.setPf(Constants.PLAN);
                orderDetailStage1.setAssemblyOrderDetail(assemblyOrderDetail);
                assemblyOrderDetailStageService.createAssemblyOrderDetailStage(orderDetailStage1);

                AssemblyOrderDetailStage orderDetailStage2 = new AssemblyOrderDetailStage();
                orderDetailStage2.setStage(stageService.getStageById(2L));
                orderDetailStage2.setQty(assemblyOrderDetail.getQty());
                orderDetailStage2.setPf(Constants.PLAN);
                orderDetailStage2.setAssemblyOrderDetail(assemblyOrderDetail);
                assemblyOrderDetailStageService.createAssemblyOrderDetailStage(orderDetailStage2);
            }
        }
    }

    public AssemblyOrder createNewOrder() {
        AssemblyOrder assemblyOrder = new AssemblyOrder();
        return assemblyOrderRepository.save(assemblyOrder);
    }

    public AssemblyOrderFormDTO buildAssemblyOrderFormDTO(AssemblyOrder assemblyOrder) {

        Long assemblyOrderId = assemblyOrder.getId();

        AssemblyOrderFormDTO assemblyOrderFormDTO = new AssemblyOrderFormDTO();
        //assemblyOrderFormDTO.setExtOrderId();
        assemblyOrderFormDTO.setId(assemblyOrder.getId());

        List<AssemblyOrderDetailDTO> assemblyOrderDetailsDTOs = assemblyOrderFormDTO.getAssemblyOrderDetails();
        List<AssemblyOrderDetail> assemblyOrderDetails = assemblyOrderDetailService.getAssemblyOrderDetailsByOrderId(assemblyOrderId);

        assemblyOrderDetails.forEach(assemblyOrderDetail -> {
            AssemblyOrderDetailDTO assemblyOrderDetailDTO = new AssemblyOrderDetailDTO();
            assemblyOrderDetailDTO.setId(assemblyOrderDetail.getId());
            assemblyOrderDetailDTO.setProduct(assemblyOrderDetail.getProduct());
            assemblyOrderDetailDTO.setQty(assemblyOrderDetail.getQty());

            assemblyOrderDetailsDTOs.add(assemblyOrderDetailDTO);
        });

        return assemblyOrderFormDTO;
    }

}
