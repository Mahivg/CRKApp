package com.auidbook.prototype.Model;

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
public class Donor {

    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("bloodgroup")
    private BloodGroup bloodGroup;
    @JsonProperty("email")
    private String email;
    @JsonProperty("cno")
    private String contactNumber;
    @JsonProperty("city")
    private String city;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("date")
    private Date dateOfBirth;
    @JsonProperty("last_donation_date")
    private Date lastDonationDate;
}
