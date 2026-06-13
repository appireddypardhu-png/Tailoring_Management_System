package com.project.Tailoring.Entities;

import java.util.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")

@JsonPropertyOrder({
        "orderid",
        "customerName",
        "customerid",
        "orderprice",
        "orderStatus",
        "orderdate",
        "subOrders"
})
public class Order {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_seq"
    )
    @SequenceGenerator(
            name = "order_seq",
            sequenceName = "order_seq",
            allocationSize = 1
    )
    private Long orderid;

    // Used during POST
    @Transient
    private Long customerid;

    // Used during GET
    @Transient
    private String customerName;

    @ManyToOne
    @JoinColumn(name = "customerid")
    @JsonIgnore
    private Customer customer;

    private Double orderprice;

    @Column(name = "orderstatus", nullable = false)
    private String orderStatus = "PENDING";

    private Date orderdate;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<SubOrder> subOrders;

    public Order() {
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public Long getCustomerid() {

        if (customer != null) {
            return customer.getCustomerid();
        }

        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public String getCustomerName() {

        if (customer != null) {
            return customer.getCname();
        }

        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(Double orderprice) {
        this.orderprice = orderprice;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<SubOrder> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(List<SubOrder> subOrders) {
        this.subOrders = subOrders;
    }
}