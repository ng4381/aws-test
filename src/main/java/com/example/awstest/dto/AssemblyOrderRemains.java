package com.example.awstest.dto;

public interface AssemblyOrderRemains {
    Long getOrderId();
    Long getExtOrderId();
    Long getOrderDetailId();
    Long getProductId();
    String getProductName();
    int getQty();
    int getQtyDone();
    Long getOperationId();
    String getOperationName();
}