package com.auidbook.prototype.api;

import com.auidbook.prototype.Model.BloodRequest;
import com.auidbook.prototype.Model.Donor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BloodDonationApi {
    @GET("/donation-requests")
    Call<List<BloodRequest>> getAllBloodRequests();

    @POST("/donation-requests")
    Call<Void> placeNewRequest(@Body BloodRequest bloodRequest);

    @POST("/donation-requests/{donation_request_id}/verify")
    Call<Void> verifyDonationRequest(@Path("donation_request_id") String bloodRequestId);

    @POST("/donation-requests/{donation_request_id}/assign")
    @FormUrlEncoded
    Call<Void> assignRequestToOrganizer(@Path("donation_request_id") String bloodRequestId,
                                        @Field("organizer_id") String organizerId);

    @POST("/donation-requests/{donation_request_id}/donate")
    @FormUrlEncoded
    Call<Void> donate(@Path("donation_request_id") String bloodRequestId,
                      @Field("donor_id") String donorId);

    @DELETE("/donation-requests/{donation_request_id}")
    Call<Void> deleteDonationRequest(@Path("donation_request_id") String bloodRequestId);

    @GET("/donation-requests/{donation_request_id}/responses")
    Call<List<Donor>> getResponses(@Path("donation_request_id") String bloodRequestId);

    @GET("/donation-requests/{donation_request_id}/responses/accept")
    @FormUrlEncoded
    Call<List<Donor>> acceptResponse(@Path("donation_request_id") String bloodRequestId, @Field("donor_id") String donorId);

    @GET("/donation-requests/{donation_request_id}/responses/decline")
    @FormUrlEncoded
    Call<List<Donor>> declineResponse(@Path("donation_request_id") String bloodRequestId, @Field("donor_id") String donorId);
}
