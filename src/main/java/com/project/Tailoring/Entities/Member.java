package com.project.Tailoring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;

@Entity
@Table(name = "member")

@JsonIgnoreProperties({
    "hibernateLazyInitializer",
    "handler"
})

@JsonPropertyOrder({
    "mid",
    "mname",
    "customerid",
    "topMeasurement",
    "bottomMeasurement"
})

public class Member {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "member_seq"
    )
    @SequenceGenerator(
        name = "member_seq",
        sequenceName = "member_seq",
        allocationSize = 1
    )
    private Long mid;

    @Column(nullable = false, length = 30)
    private String mname;

    // JSON FIELD
    @Transient
    private Long customerid;

    // DATABASE RELATION
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerid")
    @JsonIgnore
    private Customer customer;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TopMeasurement topMeasurement;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BottomMeasurement bottomMeasurement;

    public Member() {
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    // IMPORTANT
    public Long getCustomerid() {

        // During GET
        if(customer != null) {
            return customer.getCustomerid();
        }

        // During POST
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

    public TopMeasurement getTopMeasurement() {
        return topMeasurement;
    }

    public void setTopMeasurement(
            TopMeasurement topMeasurement
    ) {
        this.topMeasurement = topMeasurement;
    }

    public BottomMeasurement getBottomMeasurement() {
        return bottomMeasurement;
    }

    public void setBottomMeasurement(
            BottomMeasurement bottomMeasurement
    ) {
        this.bottomMeasurement = bottomMeasurement;
    }
}