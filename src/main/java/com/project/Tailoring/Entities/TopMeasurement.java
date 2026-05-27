package com.project.Tailoring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "top_measurement")
public class TopMeasurement {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "top_seq"
    )
    @SequenceGenerator(
        name = "top_seq",
        sequenceName = "top_seq",
        allocationSize = 1
    )
    private Long topid;

    private Double bust;
    private Double waist;
    private Double shoulder;
    private Double sleeveLength;
    private Double topLength;
    private Double neckSize;
    private Double armhole;

    @OneToOne
    @JoinColumn(name = "mid")
    @JsonIgnore
    private Member member;

    public Long getTopid() {
        return topid;
    }

    public void setTopid(Long topid) {
        this.topid = topid;
    }

    public Double getBust() {
        return bust;
    }

    public void setBust(Double bust) {
        this.bust = bust;
    }

    public Double getWaist() {
        return waist;
    }

    public void setWaist(Double waist) {
        this.waist = waist;
    }

    public Double getShoulder() {
        return shoulder;
    }

    public void setShoulder(Double shoulder) {
        this.shoulder = shoulder;
    }

    public Double getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(Double sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    public Double getTopLength() {
        return topLength;
    }

    public void setTopLength(Double topLength) {
        this.topLength = topLength;
    }

    public Double getNeckSize() {
        return neckSize;
    }

    public void setNeckSize(Double neckSize) {
        this.neckSize = neckSize;
    }

    public Double getArmhole() {
        return armhole;
    }

    public void setArmhole(Double armhole) {
        this.armhole = armhole;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}