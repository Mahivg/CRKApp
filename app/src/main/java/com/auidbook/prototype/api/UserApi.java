package com.auidbook.prototype.api;

import com.auidbook.prototype.Model.Donor;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {
    @POST("/user/{mobile_number}")
    Call<Void> update(@Path("mobile_number") String mobileNumber, @Body Donor donorDetails);

    @POST("/login")
    @FormUrlEncoded
    Call<Donor> login(@Field("mobile") String username, @Field("password") String password);

    @POST("/Doner/Register")
    Call<ResponseBody> register(@Body Donor donor);

    @GET("/logout")
    Call<Void> logout();
}
