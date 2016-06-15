package com.auidbook.prototype.Model;

import com.auidbook.prototype.Model.Fields.Address;
import com.auidbook.prototype.enums.RequestState;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mgundappan on 12-05-2016.
 */
public class BloodRequest {

    @JsonProperty("Id")
    public String id;

    @JsonProperty("RequestId")
    public String requestId;

    @JsonProperty("PatientName")
    public String patientName;

    @JsonProperty("Gender")
    public String gender;

    @JsonProperty("BloodGroup")
    public String bloodGroup;

    @JsonProperty("NoOfUnitsRequired")
    public double noOfUnitsRrequired;

    @JsonProperty("Location")
    public Address donateLocation;

    @JsonProperty("Reason")
    public String reason;

    @JsonProperty("ContactNumbers")
    public ArrayList<String> contactNumbers;

    @JsonProperty("DateOfDonation")
    public Date dateOfDonation;

    @JsonProperty("RespondedDonors")
    public ArrayList<Donor> donorResponsed;

    @JsonProperty("RequestStatus")
    public RequestState requestStatus;

    @JsonProperty("RequestedDonorId")
    public String requestedDonorId;

    public BloodRequest() {
    }

    public BloodRequest(String id, String requestId, String patientName, String gender, String bloodGroup, double noOfUnitsRrequired, Address donateLocation, String reason, ArrayList<String> contactNumbers, Date dateOfDonation, ArrayList<Donor> donorResponsed, RequestState requestStatus, String requestedDonorId) {
        this.id = id;
        this.requestId = requestId;
        this.patientName = patientName;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.noOfUnitsRrequired = noOfUnitsRrequired;
        this.donateLocation = donateLocation;
        this.reason = reason;
        this.contactNumbers = contactNumbers;
        this.dateOfDonation = dateOfDonation;
        this.donorResponsed = donorResponsed;
        this.requestStatus = requestStatus;
        this.requestedDonorId = requestedDonorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public double getNoOfUnitsRrequired() {
        return noOfUnitsRrequired;
    }

    public void setNoOfUnitsRrequired(double noOfUnitsRrequired) {
        this.noOfUnitsRrequired = noOfUnitsRrequired;
    }

    public Address getDonateLocation() {
        return donateLocation;
    }

    public void setDonateLocation(Address donateLocation) {
        this.donateLocation = donateLocation;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ArrayList<String> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(ArrayList<String> contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public Date getDateOfDonation() {
        return dateOfDonation;
    }

    public void setDateOfDonation(Date dateOfDonation) {
        this.dateOfDonation = dateOfDonation;
    }

    public ArrayList<Donor> getDonorResponsed() {
        return donorResponsed;
    }

    public void setDonorResponsed(ArrayList<Donor> donorResponsed) {
        this.donorResponsed = donorResponsed;
    }

    public RequestState getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestState requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestedDonorId() {
        return requestedDonorId;
    }

    public void setRequestedDonorId(String requestedDonorId) {
        this.requestedDonorId = requestedDonorId;
    }
}



