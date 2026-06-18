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
    private Double armhole;
    private Double blouseLength;
    private Double dressLength;
    private Double sleeveRound;
    private Double dressHip;

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

    // topLength removed

    public Double getArmhole() {
        return armhole;
    }

    public void setArmhole(Double armhole) {
        this.armhole = armhole;
    }

    public Double getBlouseLength() {
        return blouseLength;
    }

    public void setBlouseLength(Double blouseLength) {
        this.blouseLength = blouseLength;
    }

    public Double getDressLength() {
        return dressLength;
    }

    public void setDressLength(Double dressLength) {
        this.dressLength = dressLength;
    }

    public Double getSleeveRound() {
        return sleeveRound;
    }

    public void setSleeveRound(Double sleeveRound) {
        this.sleeveRound = sleeveRound;
    }

    public Double getDressHip() {
        return dressHip;
    }

    public void setDressHip(Double dressHip) {
        this.dressHip = dressHip;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}