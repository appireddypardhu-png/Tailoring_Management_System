package com.project.Tailoring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "bottom_measurement")
public class BottomMeasurement {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "bottom_seq"
    )
    @SequenceGenerator(
        name = "bottom_seq",
        sequenceName = "bottom_seq",
        allocationSize = 1
    )
    @Column(name = "bottomid")
    private Long bottomid;

    @Column(name = "waist")
    private Double waist;

    @Column(name = "hip")
    private Double hip;

    @Column(name = "thigh")
    private Double thigh;

    @Column(name = "knee_size")
    private Double kneeSize;

    @Column(name = "ankle_size")
    private Double ankleSize;

    @Column(name = "bottom_length")
    private Double bottomLength;

    @OneToOne
    @JoinColumn(name = "mid")
    @JsonIgnore
    private Member member;

    public BottomMeasurement() {
    }

    public Long getBottomid() {
        return bottomid;
    }

    public void setBottomid(Long bottomid) {
        this.bottomid = bottomid;
    }

    public Double getWaist() {
        return waist;
    }

    public void setWaist(Double waist) {
        this.waist = waist;
    }

    public Double getHip() {
        return hip;
    }

    public void setHip(Double hip) {
        this.hip = hip;
    }

    public Double getThigh() {
        return thigh;
    }

    public void setThigh(Double thigh) {
        this.thigh = thigh;
    }

    public Double getKneeSize() {
        return kneeSize;
    }

    public void setKneeSize(Double kneeSize) {
        this.kneeSize = kneeSize;
    }

    public Double getAnkleSize() {
        return ankleSize;
    }

    public void setAnkleSize(Double ankleSize) {
        this.ankleSize = ankleSize;
    }

    public Double getBottomLength() {
        return bottomLength;
    }

    public void setBottomLength(Double bottomLength) {
        this.bottomLength = bottomLength;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}