package com.project.Tailoring.DTO;

public class OrderReportDTO {

    private Long orderid;
    private String customerName;

    public OrderReportDTO(
            Long orderid,
            String customerName) {

        this.orderid = orderid;
        this.customerName = customerName;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}