package com.pineapple.rms.bean;

import java.util.Date;

public class Order {

    private Long orderId;
    private Date orderDate;
    private Long orderTotalAmount;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Long getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(Long orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }
}
