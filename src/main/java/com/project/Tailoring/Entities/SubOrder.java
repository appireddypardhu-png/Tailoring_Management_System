package com.project.Tailoring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "sub_order")
public class SubOrder {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "suborder_seq"
    )
    @SequenceGenerator(
        name = "suborder_seq",
        sequenceName = "suborder_seq",
        allocationSize = 1
    )
    private Long suborderid;

    @Transient
    private Long orderid;

    @Transient
    private Long memberid;

    @ManyToOne
    @JoinColumn(name = "orderid")
    @JsonIgnore
    private Order orders;

    @ManyToOne
    @JoinColumn(name = "memberid")
    @JsonIgnore
    private Member member;

    private String typeofdress;

    public SubOrder() {
    }

    public Long getSuborderid() {
        return suborderid;
    }

    public void setSuborderid(Long suborderid) {
        this.suborderid = suborderid;
    }

    public Long getOrderid() {

        if (orders != null) {
            return orders.getOrderid();
        }

        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public Long getMemberid() {

        if (member != null) {
            return member.getMid();
        }

        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Order getOrders() {
        return orders;
    }

    public void setOrders(Order orders) {
        this.orders = orders;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getTypeofdress() {
        return typeofdress;
    }

    public void setTypeofdress(String typeofdress) {
        this.typeofdress = typeofdress;
    }
}