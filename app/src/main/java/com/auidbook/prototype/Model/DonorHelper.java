package com.auidbook.prototype.Model;

import android.content.Context;
import android.util.Log;

import java.util.Collections;
import java.util.List;

public class DonorHelper {

    private static Donor donor;
    CRKApp crkApp;
    private List<Donor> donorList;
    private List<BloodRequest> bloodRequestList;

    public DonorHelper() {
    }

    public DonorHelper(Context context) {
        crkApp = (CRKApp) context;
    }

    public List<BloodRequest> getAllBloodRequest() {
        return bloodRequestList;
    }

    public void setAllBloodRequest(List<BloodRequest> bloodRequestList) {
        Log.i("DonorHelper", "setAllBloodRequestCalled");
        this.bloodRequestList = bloodRequestList;

    }

    public List<Donor> getAllDonor(){
        return Collections.emptyList();
    }

    public Donor getDonor(){
        if(DonorHelper.donor == null) {
            DonorHelper.donor = getAllDonor().get(0);
        }
        return DonorHelper.donor;
    }

    public void setDonor(Donor donor){

        DonorHelper.donor  = donor;
    }

    public List<BloodRequest> getApprovedBloodRequestList(){
        return  Collections.emptyList();
    }


    public boolean checkValidUser(String userName, String password){
        return false;
    }

    public Donor getDonorByUserName(String userName){
        return null;
    }

}
