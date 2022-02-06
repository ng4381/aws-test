package com.example.awstest.service;

import com.example.awstest.domain.AssemblyOrderDetail;
import com.example.awstest.domain.AssemblyOrderDetailStage;
import com.example.awstest.domain.Stage;
import com.example.awstest.dto.AssemblyOrderRemains;
import com.example.awstest.dto.AssemblyOrderRemainsDTO;
import com.example.awstest.repository.AssemblyOrderDetailStageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssemblyOrderDetailStageService {
    private AssemblyOrderDetailStageRepository assemblyOrderDetailStageRepository;
    private AssemblyOrderDetailService assemblyOrderDetailService;
    private StageService stageService;

    public AssemblyOrderDetailStageService(AssemblyOrderDetailStageRepository assemblyOrderDetailStageRepository, AssemblyOrderDetailService assemblyOrderDetailService, StageService stageService) {
        this.assemblyOrderDetailStageRepository = assemblyOrderDetailStageRepository;
        this.assemblyOrderDetailService = assemblyOrderDetailService;
        this.stageService = stageService;
    }

    public List<AssemblyOrderRemains> getAllRemains() {
        return assemblyOrderDetailStageRepository.getAllAssemblyOrderRemains();
    }

    public void createAssemblyOrderDetailStage(AssemblyOrderDetailStage orderDetailStage) {
        assemblyOrderDetailStageRepository.save(orderDetailStage);
    }

    public void createAssemblyOrderDetailStageFact(List<AssemblyOrderRemainsDTO> assemblyOrderRemains) {

        List<AssemblyOrderDetailStage> listOrderDetailStage = new ArrayList<>();

        for (AssemblyOrderRemainsDTO orderRemainsDTO : assemblyOrderRemains) {
            AssemblyOrderDetail orderDetail = assemblyOrderDetailService.getAssemblyOrderDetailById(orderRemainsDTO.getOrderDetailId());
            Stage stage = stageService.getStageById(orderRemainsDTO.getStageId());

            AssemblyOrderDetailStage orderDetailStage = new AssemblyOrderDetailStage();
            orderDetailStage.setAssemblyOrderDetail(orderDetail);
            orderDetailStage.setStage(stage);
            orderDetailStage.setPf("f");
            orderDetailStage.setQty(orderRemainsDTO.getQtyDone());

            listOrderDetailStage.add(orderDetailStage);
        }
        assemblyOrderDetailStageRepository.saveAll(listOrderDetailStage);
    }

    public List<AssemblyOrderDetailStage> findByAssemblyOrderDetailId(Long id) {
        return assemblyOrderDetailStageRepository.findByAssemblyOrderDetailId(id);
    }
}
