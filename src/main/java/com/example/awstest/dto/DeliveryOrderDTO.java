package com.example.awstest.dto;

import java.util.List;

public class DeliveryOrderDTO {
    private int extOrderId;
    private List<DeliveryOrderDTO> deliveryOrders;
}

class DeliveryOrderDetailDTO {
    private int product_id;
    private int product_name;
    private int qty;
}