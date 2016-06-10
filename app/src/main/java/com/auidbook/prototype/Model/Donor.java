package com.auidbook.prototype.Model;

import com.auidbook.prototype.Model.Fields.Address;

import java.util.ArrayList;

import java.util.Date;

/**
 * Created by mgundappan on 12-05-2016.
 */
public class Donor {

    private String donorID;
    private String donorName;
    private String gender;
    private int age;
    private Date dateOfBitrth;
    private String bloodGroup;
    private String memberType;
    private ArrayList<Address> addresses;
    private String mobileNumber;
    private byte[] profilePicture;
    private ArrayList<BloodRequest> requsetForBlood;
    private ArrayList<BloodRequest> donationList;
    private boolean isRequestAccepted;

    public Donor(){

    }

    public Donor(String donorID, String donorName, String gender, int age, Date dateOfBitrth, String bloodGroup, String memberType, ArrayList<Address> addresses, String mobileNumber, byte[] profilePicture, ArrayList<BloodRequest> requsetForBlood, ArrayList<BloodRequest> donationList, boolean isRequestAccepted) {
        this.donorID = donorID;
        this.donorName = donorName;
        this.gender = gender;
        this.age = age;
        this.dateOfBitrth = dateOfBitrth;
        this.bloodGroup = bloodGroup;
        this.memberType = memberType;
        this.addresses = addresses;
        this.mobileNumber = mobileNumber;
        this.profilePicture = profilePicture;
        this.requsetForBlood = requsetForBlood;
        this.donationList = donationList;
        this.isRequestAccepted = isRequestAccepted;
    }

    public String getDonorID() {
        return donorID;
    }

    public void setDonorID(String donorID) {
        this.donorID = donorID;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDateOfBitrth() {
        return dateOfBitrth;
    }

    public void setDateOfBitrth(Date dateOfBitrth) {
        this.dateOfBitrth = dateOfBitrth;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public ArrayList<BloodRequest> getRequsetForBlood() {
        return requsetForBlood;
    }

    public void setRequsetForBlood(ArrayList<BloodRequest> requsetForBlood) {
        this.requsetForBlood = requsetForBlood;
    }

    public ArrayList<BloodRequest> getDonationList() {
        return donationList;
    }

    public void setDonationList(ArrayList<BloodRequest> donationList) {
        this.donationList = donationList;
    }

    public boolean isRequestAccepted() {
        return isRequestAccepted;
    }

    public void setIsRequestAccepted(boolean isRequestAccepted) {
        this.isRequestAccepted = isRequestAccepted;
    }
}
