package com.employee.entity;

import java.io.Serializable;

public class EmployeeAddressDetails implements Serializable {

    private String houseName;
    private String streetName;
    private String city;
    private String state;
    private Integer pincode;

    public EmployeeAddressDetails() {
    }

    @Override
    public String toString() {
        return "EmployeeAddressDetails{" +
                "houseName='" + houseName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode=" + pincode +
                '}';
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPincode() {
        return pincode;
    }

    public void setPincode(Integer pincode) {
        this.pincode = pincode;
    }

    public EmployeeAddressDetails(String houseName, String streetName, String city, String state, Integer pincode) {
        this.houseName = houseName;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }
}


//    private Integer employeeId;
//    private String permanentAddrressLane1;
//    private String permanentAddrressLane2;
//    private String permanentAddrressStreetName;
//    private String permanentAddrressCity;
//    private String permanentAddrressState;
//    private Integer permanentAddrressPincode;
//
//    private String temporaryAddrressLane1;
//    private String temporaryAddrressLane2;
//    private String temporaryAddrressStreetName;
//    private String temporaryAddrressCity;
//    private String temporaryAddrressState;
//    private Integer temporaryAddrressPincode;

//    private String addrressLane1;
//    private String addrressLane2;
//    private String streetName;
//    private String city;
//    private String state;
//    private Integer pincode;
//
//    public String getAddrressLane1() {
//        return addrressLane1;
//    }
//
//    public void setAddrressLane1(String addrressLane1) {
//        this.addrressLane1 = addrressLane1;
//    }
//
//    public String getAddrressLane2() {
//        return addrressLane2;
//    }
//
//    public void setAddrressLane2(String addrressLane2) {
//        this.addrressLane2 = addrressLane2;
//    }
//
//    public String getStreetName() {
//        return streetName;
//    }
//
//    public void setStreetName(String streetName) {
//        this.streetName = streetName;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public Integer getPincode() {
//        return pincode;
//    }
//
//    public void setPincode(Integer pincode) {
//        this.pincode = pincode;
//    }
//
//    public EmployeeAddressDetails(String addrressLane1, String addrressLane2, String streetName, String city, String state, Integer pincode) {
//        this.addrressLane1 = addrressLane1;
//        this.addrressLane2 = addrressLane2;
//        this.streetName = streetName;
//        this.city = city;
//        this.state = state;
//        this.pincode = pincode;
//    }
//
//    public EmployeeAddressDetails() {
//    }
//
//    @Override
//    public String toString() {
//        return "EmployeeAddressDetails{" +
//                "addrressLane1='" + addrressLane1 + '\'' +
//                ", addrressLane2='" + addrressLane2 + '\'' +
//                ", streetName='" + streetName + '\'' +
//                ", city='" + city + '\'' +
//                ", state='" + state + '\'' +
//                ", pincode=" + pincode +
//                '}';
//    }
