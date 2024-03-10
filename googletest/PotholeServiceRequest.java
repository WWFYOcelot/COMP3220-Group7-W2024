package com.example.googletest;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PotholeServiceRequest {
    @SerializedName("Service Request Description")
    public String serviceRequestDescription;
    @SerializedName("Department")
    public String department;
    @SerializedName("Method Received")
    public String methodReceived;
    @SerializedName("Created Date")
    public String createdDate;
    @SerializedName("Block/Address")
    public String blockAddress;
    @SerializedName("Street")
    public String street;
    @SerializedName("Ward")
    public String ward;
    @SerializedName("Sub-Type")
    public String subType;

    @Override
    public String toString() {
        return "PotholeServiceRequest{" +
                "serviceRequestDescription='" + serviceRequestDescription + '\'' +
                ", department='" + department + '\'' +
                ", methodReceived='" + methodReceived + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", blockAddress='" + blockAddress + '\'' +
                ", street='" + street + '\'' +
                ", ward='" + ward + '\'' +
                ", subType='" + subType + '\'' +
                '}';
    }
}
