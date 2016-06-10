package com.auidbook.prototype.Model;

import android.app.Application;

/**
 * Created by mgundappan on 02-06-2016.
 */
public class CRKApp extends Application {

    private Donor donor;
    private DonorHelper donorHelper;

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public DonorHelper getDonorHelper() {
        return donorHelper;
    }

    public void setDonorHelper(DonorHelper donorHelper) {
        this.donorHelper = donorHelper;
    }
}
