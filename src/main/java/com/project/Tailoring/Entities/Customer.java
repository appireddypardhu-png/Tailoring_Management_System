package com.project.Tailoring.Entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "customer_seq"
    )
    @SequenceGenerator(
        name = "customer_seq",
        sequenceName = "customer_seq",
        allocationSize = 1
    )
    private Long customerid;

    @Column(nullable = false, length = 30)
    private String cname;

    @Column(nullable = false, unique = true, length = 15)
    private String cphone;

    @OneToMany(mappedBy = "customer")
    private List<Member> members;

    public Customer() {}

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}