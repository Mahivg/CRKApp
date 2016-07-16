package com.auidbook.prototype.listener;

import android.support.v4.app.Fragment;

import com.auidbook.prototype.Model.BloodRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgundappan on 13-06-2016.
 */
public interface ICommunicator {

    List<BloodRequest> getBloodRequset();

    void setBloodRequset(List<BloodRequest> requsetBloodList);

    void changeFragment(Fragment fragment);

}
