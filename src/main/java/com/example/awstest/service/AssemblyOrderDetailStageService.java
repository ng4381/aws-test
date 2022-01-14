package com.example.awstest.service;

import com.example.awstest.DAO.AssemblyOrderRemains;
import com.example.awstest.DAO.AssemblyOrderRemainsDTO;
import com.example.awstest.DAO.TestDTO;
import com.example.awstest.domain.AssemblyOrderDetail;
import com.example.awstest.domain.AssemblyOrderDetailStage;
import com.example.awstest.domain.Stage;
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

    public List<TestDTO> getTestDTO() {
        return assemblyOrderDetailStageRepository.gettestDTO();
    }

    public void createAssemblyOrderDetailStage(AssemblyOrderDetailStage orderDetailStage) {
        assemblyOrderDetailStageRepository.save(orderDetailStage);
    }

    public void createAssemblyOrderDetailStageFact(List<AssemblyOrderRemainsDTO> assemblyOrderRemains) {

        List<AssemblyOrderDetailStage> listOrderDetailStage = new ArrayList<>();

        for(AssemblyOrderRemainsDTO orderRemainsDTO : assemblyOrderRemains) {
            AssemblyOrderDetail orderDetail = assemblyOrderDetailService.getAssemblyOrderDetailById(orderRemainsDTO.getOrderDetailId());
            Stage stage = stageService.getStageById(orderRemainsDTO.getOperationId());

            AssemblyOrderDetailStage orderDetailStage = new AssemblyOrderDetailStage();
            orderDetailStage.setAssemblyOrderDetail(orderDetail);
            orderDetailStage.setStage(stage);
            orderDetailStage.setPf("f");
            orderDetailStage.setQty(orderRemainsDTO.getQtyDone());

            listOrderDetailStage.add(orderDetailStage);
        }
        assemblyOrderDetailStageRepository.saveAll(listOrderDetailStage);
    }
}
