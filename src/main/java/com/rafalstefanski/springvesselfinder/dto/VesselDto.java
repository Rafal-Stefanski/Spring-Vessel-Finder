package com.rafalstefanski.springvesselfinder.dto;

import javax.persistence.*;

@Entity
@Table(name = "vessels_list")
public class VesselDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String callSign;
    private String name;
    private String country;
    private Integer shipType;
    private String destination;

    public VesselDto() {
    }

    public VesselDto(String callSign, String name, String country, Integer shipType, String destination) {
        this.callSign = callSign;
        this.name = name;
        this.country = country;
        this.shipType = shipType;
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getShipType() {
        return shipType;
    }

    public void setShipType(Integer shipType) {
        this.shipType = shipType;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "VesselDto{" +
                "id=" + id +
                ", callSign='" + callSign + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", shipType='" + shipType + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
