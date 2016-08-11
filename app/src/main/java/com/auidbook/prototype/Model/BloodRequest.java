package com.auidbook.prototype.Model;

import com.auidbook.prototype.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class BloodRequest {

    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("pat_name")
    private String patientName;
    @JsonProperty("bloodgroup")
    private BloodGroup bloodGroup;
    @JsonProperty("age")
    private int age;
    @JsonProperty("emergencytype")
    private EmergencyType emergencyType = EmergencyType.URGENT;
    @JsonProperty("date")
    private Date donationDate;
    @JsonProperty("time")
    private String donationTime;
    @JsonProperty("units")
    private double numberOfUnits;
    @JsonProperty("mobileno")
    private String contactNumber;
    @JsonProperty("location")
    private String locality;
    @JsonProperty("hospital")
    private String hospitalName;
    @JsonProperty("address")
    public String address;
    @JsonProperty("city")
    public String city;
    @JsonProperty("state")
    public String state;
    @JsonProperty("status")
    public RequestStatus requestStatus;
}



