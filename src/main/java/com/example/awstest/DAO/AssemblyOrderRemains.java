package com.example.awstest.DAO;

public interface AssemblyOrderRemains {
    int getOrderId();
    int getProductId();
    String getProductName();
    int getQty();
    int getQtyDone();
    int getOperationId();
    String getOperationName();
}
