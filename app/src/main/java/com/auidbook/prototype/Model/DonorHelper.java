package com.auidbook.prototype.Model;

import android.content.Context;
import android.util.Log;

import com.auidbook.prototype.util.RetrofitUtils;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonorHelper {

    private static Donor donor;
    CRKApp crkApp;
    private List<Donor> donorList;
    private List<BloodRequest> bloodRequestList;

    public DonorHelper() {
    }

    public DonorHelper(Context context) {
//        crkApp = (CRKApp) context;
    }

    public List<BloodRequest> getAllBloodRequest() {
        return bloodRequestList;
    }

    public void setAllBloodRequest(List<BloodRequest> bloodRequestList) {
        Log.i("DonorHelper", "setAllBloodRequestCalled");
        this.bloodRequestList = bloodRequestList;

    }

    public List<Donor> getAllDonor() {
        return Collections.emptyList();
    }

    public Donor getDonor() {
        if (DonorHelper.donor == null) {
            DonorHelper.donor = getAllDonor().get(0);
        }
        return DonorHelper.donor;
    }

    public void setDonor(Donor donor) {

        DonorHelper.donor = donor;
    }

    public List<BloodRequest> getApprovedBloodRequestList() {
        return Collections.emptyList();
    }


    public void checkValidUser(String userName, String password, final Function<Donor> loginResultProcessor) {
        RetrofitUtils.getUserApi().login(userName, password).enqueue(new Callback<Donor>() {
            @Override
            public void onResponse(Call<Donor> call, Response<Donor> response) {
                if (response.isSuccessful()) {
                    loginResultProcessor.apply(response.body());
                } else {
                    Log.e(DonorHelper.class.getName(), "Response status: " + response.code() + " message: " + response.message());
                    loginResultProcessor.apply(null);
                }
            }

            @Override
            public void onFailure(Call<Donor> call, Throwable t) {
                Log.e(DonorHelper.class.getName(), t.getMessage(), t);
                loginResultProcessor.apply(null);
            }
        });
    }

    public Donor getDonorByUserName(String userName) {
        return null;
    }

}
