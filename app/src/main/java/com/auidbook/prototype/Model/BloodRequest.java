package com.auidbook.prototype.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.auidbook.prototype.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class BloodRequest implements Parcelable {

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

    public String getRequestId() {
        return this.requestId;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public BloodGroup getBloodGroup() {
        return this.bloodGroup;
    }

    public int getAge() {
        return this.age;
    }

    public EmergencyType getEmergencyType() {
        return this.emergencyType;
    }

    public Date getDonationDate() {
        return this.donationDate;
    }

    public String getDonationTime() {
        return this.donationTime;
    }

    public double getNumberOfUnits() {
        return this.numberOfUnits;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public String getLocality() {
        return this.locality;
    }

    public String getHospitalName() {
        return this.hospitalName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public RequestStatus getRequestStatus() {
        return this.requestStatus;
    }

    public String getFormattedDonationDate () {
        return new SimpleDateFormat("EEE, MMM d, yyyy").format(this.donationDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.requestId);
        dest.writeString(this.patientName);
        dest.writeInt(this.bloodGroup == null ? -1 : this.bloodGroup.ordinal());
        dest.writeInt(this.age);
        dest.writeInt(this.emergencyType == null ? -1 : this.emergencyType.ordinal());
        dest.writeLong(this.donationDate != null ? this.donationDate.getTime() : -1);
        dest.writeString(this.donationTime);
        dest.writeDouble(this.numberOfUnits);
        dest.writeString(this.contactNumber);
        dest.writeString(this.locality);
        dest.writeString(this.hospitalName);
        dest.writeString(this.address);
        dest.writeString(this.city);
        dest.writeString(this.state);
        dest.writeInt(this.requestStatus == null ? -1 : this.requestStatus.ordinal());
    }

    protected BloodRequest(Parcel in) {
        this.requestId = in.readString();
        this.patientName = in.readString();
        int tmpBloodGroup = in.readInt();
        this.bloodGroup = tmpBloodGroup == -1 ? null : BloodGroup.values()[tmpBloodGroup];
        this.age = in.readInt();
        int tmpEmergencyType = in.readInt();
        this.emergencyType = tmpEmergencyType == -1 ? null : EmergencyType.values()[tmpEmergencyType];
        long tmpDonationDate = in.readLong();
        this.donationDate = tmpDonationDate == -1 ? null : new Date(tmpDonationDate);
        this.donationTime = in.readString();
        this.numberOfUnits = in.readDouble();
        this.contactNumber = in.readString();
        this.locality = in.readString();
        this.hospitalName = in.readString();
        this.address = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        int tmpRequestStatus = in.readInt();
        this.requestStatus = tmpRequestStatus == -1 ? null : RequestStatus.values()[tmpRequestStatus];
    }

    public static final Creator<BloodRequest> CREATOR = new Creator<BloodRequest>() {
        @Override
        public BloodRequest createFromParcel(Parcel source) {
            return new BloodRequest(source);
        }

        @Override
        public BloodRequest[] newArray(int size) {
            return new BloodRequest[size];
        }
    };
}



