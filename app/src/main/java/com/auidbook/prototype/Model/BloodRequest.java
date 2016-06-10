package com.auidbook.prototype.Model;

import com.auidbook.prototype.Model.Fields.Address;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mgundappan on 12-05-2016.
 */
public class BloodRequest {

    public String requestId;

    public String patientName;

    public String gender;

    public String bloodGroup;

    public double noOfUnitsRrequired;

    public Address donateLocation;

    public String reason;

    public ArrayList<String> contactNumbers;

    public Date dateOfDonation;

    public ArrayList<Donor> donorResponsed;

    public String requestStatus;

    public String requestedDonorId;

    public BloodRequest() {
    }

    public BloodRequest(String requestId, String patientName, String gender, String bloodGroup, double noOfUnitsRrequired, Address donateLocation, String reason,ArrayList<String> contactNumbers, Date dateOfDonation, ArrayList<Donor> donorResponsed, String requestStatus, String requestedDonorId) {
        this.requestId = requestId;
        this.patientName = patientName;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.noOfUnitsRrequired = noOfUnitsRrequired;
        this.donateLocation = donateLocation;
        this.reason = reason;
        this.contactNumbers = contactNumbers;
        this.dateOfDonation = dateOfDonation;
        this.donorResponsed = new ArrayList<Donor>();
        this.requestStatus = requestStatus;
        this.requestedDonorId = requestedDonorId;

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

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestedDonorId() {
        return requestedDonorId;
    }

    public void setRequestedDonorId(String requestedDonorId) {
        this.requestedDonorId = requestedDonorId;
    }
}



