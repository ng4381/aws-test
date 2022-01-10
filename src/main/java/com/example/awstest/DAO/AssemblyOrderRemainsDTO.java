package com.example.awstest.DAO;

public class AssemblyOrderRemainsDTO {
    private Long orderId;
    private Long extOrderId;
    private Long orderDetailId;
    private Long productId;
    private String productName;
    private int qty;
    private int qtyDone;
    private Long operationId;
    private String operationName;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getExtOrderId() {
        return extOrderId;
    }

    public void setExtOrderId(Long extOrderId) {
        this.extOrderId = extOrderId;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getQtyDone() {
        return qtyDone;
    }

    public void setQtyDone(int qtyDone) {
        this.qtyDone = qtyDone;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
