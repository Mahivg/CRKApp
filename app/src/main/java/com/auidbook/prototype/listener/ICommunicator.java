package com.auidbook.prototype.listener;

import com.auidbook.prototype.Model.BloodRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mgundappan on 13-06-2016.
 */
public interface ICommunicator {

    List<BloodRequest> getBloodRequset();

    void setBloodRequset(List<BloodRequest> requsetBloodList);


}
