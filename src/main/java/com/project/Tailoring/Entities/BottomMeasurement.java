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

    @Column(name = "pant_length")
    private Double pantLength;

    @Column(name = "mori")
    private Double mori;

    @Column(name = "thigh_round")
    private Double thighRound;

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

    public Double getPantLength() {
        return pantLength;
    }

    public void setPantLength(Double pantLength) {
        this.pantLength = pantLength;
    }

    public Double getMori() {
        return mori;
    }

    public void setMori(Double mori) {
        this.mori = mori;
    }

    public Double getThighRound() {
        return thighRound;
    }

    public void setThighRound(Double thighRound) {
        this.thighRound = thighRound;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}