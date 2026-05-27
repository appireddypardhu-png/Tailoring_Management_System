package com.project.Tailoring.Entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
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

    @Transient
    private Long customerid;

    @ManyToOne
    @JoinColumn(name = "customerid")
    @JsonIgnore
    private Customer customer;

    private Double orderprice;

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

    public List<SubOrder> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(List<SubOrder> subOrders) {
        this.subOrders = subOrders;
    }
}