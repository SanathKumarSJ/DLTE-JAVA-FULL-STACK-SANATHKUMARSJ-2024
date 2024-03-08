package org.consoleapp;

import java.io.Serializable;

public class EmployeeAddressDetails implements Serializable {

    private Integer employeeId;
    private String permanentAddrressLane1;
    private String permanentAddrressLane2;
    private String permanentAddrressStreetName;
    private String permanentAddrressCity;
    private String permanentAddrressState;
    private Integer permanentAddrressPincode;

    private String temporaryAddrressLane1;
    private String temporaryAddrressLane2;
    private String temporaryAddrressStreetName;
    private String temporaryAddrressCity;
    private String temporaryAddrressState;
    private Integer temporaryAddrressPincode;

    public EmployeeAddressDetails(String permanent1, String permanent2, String permanentStreet, String permanentCity, String permanentState, Integer permanentPin) {
    }

    @Override
    public String toString() {
        return "EmployeeAddressDetails{" +
                "permanentAddrressLane1='" + permanentAddrressLane1 + '\'' +
                ", permanentAddrressLane2='" + permanentAddrressLane2 + '\'' +
                ", permanentAddrressStreetName='" + permanentAddrressStreetName + '\'' +
                ", permanentAddrressCity='" + permanentAddrressCity + '\'' +
                ", permanentAddrressState='" + permanentAddrressState + '\'' +
                ", permanentAddrressPincode=" + permanentAddrressPincode +
                ", temporaryAddrressLane1='" + temporaryAddrressLane1 + '\'' +
                ", temporaryAddrressLane2='" + temporaryAddrressLane2 + '\'' +
                ", temporaryAddrressStreetName='" + temporaryAddrressStreetName + '\'' +
                ", temporaryAddrressCity='" + temporaryAddrressCity + '\'' +
                ", temporaryAddrressState='" + temporaryAddrressState + '\'' +
                ", temporaryAddrressPincode=" + temporaryAddrressPincode +
                '}';
    }

    public EmployeeAddressDetails(String permanentAddrressLane1, String permanentAddrressLane2, String permanentAddrressStreetName, String permanentAddrressCity, String permanentAddrressState, Integer permanentAddrressPincode, String temporaryAddrressLane1, String temporaryAddrressLane2, String temporaryAddrressStreetName, String temporaryAddrressCity, String temporaryAddrressState, Integer temporaryAddrressPincode) {
        this.permanentAddrressLane1 = permanentAddrressLane1;
        this.permanentAddrressLane2 = permanentAddrressLane2;
        this.permanentAddrressStreetName = permanentAddrressStreetName;
        this.permanentAddrressCity = permanentAddrressCity;
        this.permanentAddrressState = permanentAddrressState;
        this.permanentAddrressPincode = permanentAddrressPincode;
        this.temporaryAddrressLane1 = temporaryAddrressLane1;
        this.temporaryAddrressLane2 = temporaryAddrressLane2;
        this.temporaryAddrressStreetName = temporaryAddrressStreetName;
        this.temporaryAddrressCity = temporaryAddrressCity;
        this.temporaryAddrressState = temporaryAddrressState;
        this.temporaryAddrressPincode = temporaryAddrressPincode;
    }

    public String getPermanentAddrressLane1() {
        return permanentAddrressLane1;
    }

    public void setPermanentAddrressLane1(String permanentAddrressLane1) {
        this.permanentAddrressLane1 = permanentAddrressLane1;
    }

    public String getPermanentAddrressLane2() {
        return permanentAddrressLane2;
    }

    public void setPermanentAddrressLane2(String permanentAddrressLane2) {
        this.permanentAddrressLane2 = permanentAddrressLane2;
    }

    public String getPermanentAddrressStreetName() {
        return permanentAddrressStreetName;
    }

    public void setPermanentAddrressStreetName(String permanentAddrressStreetName) {
        this.permanentAddrressStreetName = permanentAddrressStreetName;
    }

    public String getPermanentAddrressCity() {
        return permanentAddrressCity;
    }

    public void setPermanentAddrressCity(String permanentAddrressCity) {
        this.permanentAddrressCity = permanentAddrressCity;
    }

    public String getPermanentAddrressState() {
        return permanentAddrressState;
    }

    public void setPermanentAddrressState(String permanentAddrressState) {
        this.permanentAddrressState = permanentAddrressState;
    }

    public Integer getPermanentAddrressPincode() {
        return permanentAddrressPincode;
    }

    public void setPermanentAddrressPincode(Integer permanentAddrressPincode) {
        this.permanentAddrressPincode = permanentAddrressPincode;
    }

    public String getTemporaryAddrressLane1() {
        return temporaryAddrressLane1;
    }

    public void setTemporaryAddrressLane1(String temporaryAddrressLane1) {
        this.temporaryAddrressLane1 = temporaryAddrressLane1;
    }

    public String getTemporaryAddrressLane2() {
        return temporaryAddrressLane2;
    }

    public void setTemporaryAddrressLane2(String temporaryAddrressLane2) {
        this.temporaryAddrressLane2 = temporaryAddrressLane2;
    }

    public String getTemporaryAddrressStreetName() {
        return temporaryAddrressStreetName;
    }

    public void setTemporaryAddrressStreetName(String temporaryAddrressStreetName) {
        this.temporaryAddrressStreetName = temporaryAddrressStreetName;
    }

    public String getTemporaryAddrressCity() {
        return temporaryAddrressCity;
    }

    public void setTemporaryAddrressCity(String temporaryAddrressCity) {
        this.temporaryAddrressCity = temporaryAddrressCity;
    }

    public String getTemporaryAddrressState() {
        return temporaryAddrressState;
    }

    public void setTemporaryAddrressState(String temporaryAddrressState) {
        this.temporaryAddrressState = temporaryAddrressState;
    }

    public Integer getTemporaryAddrressPincode() {
        return temporaryAddrressPincode;
    }

    public void setTemporaryAddrressPincode(Integer temporaryAddrressPincode) {
        this.temporaryAddrressPincode = temporaryAddrressPincode;
    }
//    public void display1(){
//        System.out.println("Hi this is getter setter");
    }
