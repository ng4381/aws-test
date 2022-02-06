package com.example.awstest.dto;

import com.example.awstest.domain.AssemblyOrderDetail;
import com.example.awstest.domain.Product;
import com.example.awstest.domain.Stage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class AssemblyOrderFormDTO {
    private boolean isNew;
    private Long id;
    private Long extOrderId;
    private List<AssemblyOrderDetailDTO> assemblyOrderDetails = new ArrayList<>();

    @Getter
    @Setter
    public static class AssemblyOrderDetailDTO {
        private boolean isNew;
        private Long id;
        private Product product;
        private int qty;
        private List<AssemblyOrderDetailStagesDTO> assemblyOrderDetailStages = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class AssemblyOrderDetailStagesDTO {
        private boolean isNew;
        private Long id;
        private int qty;
        private LocalDateTime dateTime;
        private String pf;
        private AssemblyOrderDetail assemblyOrderDetail;
        private Stage stage;
    }
}