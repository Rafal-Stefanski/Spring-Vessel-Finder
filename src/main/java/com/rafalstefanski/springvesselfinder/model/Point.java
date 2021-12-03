package com.rafalstefanski.springvesselfinder.model;

public class Point {

    private double x;
    private double y;
    private String shipType;
    private String name;
    private String destination;

    public Point() {
    }

    public Point(double x, double y, String shipType, String name, String destination) {
        this.x = x;
        this.y = y;
        this.shipType = shipType;
        this.name = name;
        this.destination = destination;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
