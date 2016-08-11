package com.auidbook.prototype.util;

import android.support.annotation.NonNull;

import com.auidbook.prototype.BuildConfig;
import com.auidbook.prototype.api.BloodDonationApi;
import com.auidbook.prototype.api.UserApi;

import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitUtils {
    public static BloodDonationApi getBloodDonationApi() {
        return createRetrofit()
                .create(BloodDonationApi.class);
    }

    @NonNull
    private static Retrofit createRetrofit() {
        return new Retrofit
                .Builder()
                .client(new OkHttpClient.Builder().build())
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public static UserApi getUserApi() {
        return createRetrofit()
                .create(UserApi.class);
    }
}
