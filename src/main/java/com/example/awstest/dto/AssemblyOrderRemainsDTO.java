package com.example.awstest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class AssemblyOrderRemainsDTO {
    private Long orderId;
    private Long extOrderId;
    private Long orderDetailId;
    private Long productId;
    private String productName;
    private int qty;
    private int qtyDone;
    private Long stageId;
    private String stageName;
}