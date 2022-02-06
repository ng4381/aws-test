package com.example.awstest.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class AssemblyOrderRemainsDTO {
    @NonNull
    private Long orderId;
    @NonNull
    private Long extOrderId;
    @NonNull
    private Long orderDetailId;
    @NonNull
    private Long productId;
    @NonNull
    private String productName;
    @NonNull
    private int qty;
    @NonNull
    private int qtyDone;
    @NonNull
    private Long operationId;
    @NonNull
    private String operationName;
}