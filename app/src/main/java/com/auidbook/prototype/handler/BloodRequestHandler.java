package com.auidbook.prototype.handler;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.auidbook.prototype.BuildConfig;
import com.auidbook.prototype.Model.BloodRequest;
import com.auidbook.prototype.listener.IBloodRequest;
import com.auidbook.prototype.listener.ICommunicator;
import com.auidbook.prototype.util.RetrofitUtils;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import retrofit2.Response;

public class BloodRequestHandler implements IBloodRequest {

    private ICommunicator communicator;
    private Context mContext;

    public BloodRequestHandler(ICommunicator communicator, Context mContext) {
        this.communicator = communicator;
        this.mContext = mContext;
    }

    @Override
    public void getAllBloodRequest() {
        GetAllBloodRequestTask getAllBloodRequestTask = new GetAllBloodRequestTask();
        getAllBloodRequestTask.execute();
    }

    @Override
    public void updateBloodRequest() {

    }

    @Override
    public void getBloodRequestByDonorId() {

    }

    class GetAllBloodRequestTask extends AsyncTask<Void, String, List<BloodRequest>> {
        ProgressDialog progress = new ProgressDialog(mContext);


        @Override
        protected void onPreExecute() {
            progress.setTitle("Loading");
            progress.setMessage("Connecting to Server...");
            progress.show();
            progress.setCancelable(false);
        }

        @Override
        protected List<BloodRequest> doInBackground(Void... params) {
            try {
                return RetrofitUtils.getBloodDonationApi().getAllBloodRequests().execute().body();
            } catch (Exception e) {
                Log.e(BloodRequestHandler.class.getName(), e.getMessage(), e);
                e.printStackTrace();
                cancel(true);
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<BloodRequest> bloodRequests) {
            communicator.setBloodRequset(bloodRequests);
            progress.dismiss();
        }
    }

}
