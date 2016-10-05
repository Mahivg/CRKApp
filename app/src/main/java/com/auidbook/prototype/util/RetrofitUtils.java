package com.auidbook.prototype.util;

import android.support.annotation.NonNull;

import com.auidbook.prototype.BuildConfig;
import com.auidbook.prototype.api.BloodDonationApi;
import com.auidbook.prototype.api.UserApi;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitUtils {
    public static BloodDonationApi getBloodDonationApi() {
        return createRetrofit()
                .create(BloodDonationApi.class);
    }

    @NonNull
    private static Retrofit createRetrofit() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return new Retrofit
                .Builder()
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY)
                        ).build())
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();
    }

    public static UserApi getUserApi() {
        return createRetrofit()
                .create(UserApi.class);
    }
}
