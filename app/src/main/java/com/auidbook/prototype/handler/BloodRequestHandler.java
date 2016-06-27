package com.auidbook.prototype.handler;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.auidbook.prototype.AppConstants;
import com.auidbook.prototype.LoginActivity;
import com.auidbook.prototype.MainActivity;
import com.auidbook.prototype.Model.BloodRequest;
import com.auidbook.prototype.Model.Fields.User;
import com.auidbook.prototype.listener.IBloodRequest;
import com.auidbook.prototype.listener.ICommunicator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by mgundappan on 13-06-2016.
 */
public class BloodRequestHandler implements IBloodRequest{

    private RestOperations asyncRestTemplate = new RestTemplate();
    private ICommunicator communicator;
    private Context mContext;

    public BloodRequestHandler(ICommunicator communicator, Context mContext) {
        this.communicator = communicator;
        this.mContext = mContext;
    }

    @Override
    public void getAllBloodRequset() {
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
            //super.onPreExecute();
            Log.i("Doin Back", "Pre execute");
            progress.setTitle("Loading");
            progress.setMessage("Connecting to Server...");
            progress.show();
            progress.setCancelable(false);
        }

        @Override
        protected List<BloodRequest> doInBackground(Void... params) {

            List<BloodRequest> bloodRequestList = null;
            BloodRequest bloodRequest = null;
            Uri.Builder builder = new Uri.Builder();
            builder.scheme(AppConstants.SCHEME)
                    .encodedAuthority(AppConstants.AUTHORITY)
                    .appendPath("api")
                    .appendPath("requests")
                    .appendPath("GetAll");


            String getAllBloodRequestUri = builder.build().toString();

            Log.i("URL : ", getAllBloodRequestUri);
            try {
                Log.i("Doin Back", "Called");
                ((RestTemplate)asyncRestTemplate).getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                try {

                    ParameterizedTypeReference<List<BloodRequest>> parameterizedTypeReference = new ParameterizedTypeReference<List<BloodRequest>>() {};
                    Log.i("Doin Back", "Ptr declaration");
                    // ResponseEntity<List<BloodRequest>> response = asyncRestTemplate.exchange(getAllBloodRequestUri,HttpMethod.GET,null,parameterizedTypeReference);
                    Log.i("Doin Back", "response call before");
                    ResponseEntity<List<BloodRequest>> response = asyncRestTemplate.exchange(getAllBloodRequestUri,HttpMethod.GET,null, parameterizedTypeReference);

                    //ResponseEntity<BloodRequest> response = asyncRestTemplate.exchange(getAllBloodRequestUri,HttpMethod.GET,null,BloodRequest.class);
                    //bloodRequest= response.getBody();
                    Log.i("Doin Back", "response called");
                    bloodRequestList= response.getBody();
                    Log.i("Doin Back", "End Of Try");

                } catch (Exception e) {

                    Log.i("Doin Back", "Exception caught");
                    e.printStackTrace();
                }
            } catch (RestClientException e) {
                Log.i("Doin Back", "Rest Exception caught");
                cancel(true);

            }
            Log.i("Doin Back", "return List");
            return bloodRequestList;
        }

        @Override
        protected void onPostExecute(List<BloodRequest> bloodRequests) {
            //super.onPostExecute(bloodRequests);
            Log.i("Doin Back", "Post Called");

            communicator.setBloodRequset(bloodRequests);
            progress.dismiss();


        }
    }

    /*private List<BloodRequest> bindResponse(List<LinkedHashMap<String, Object>> pendingRequestMap) throws IOException{


        List<BloodRequest> requestBloodList = new ArrayList<BloodRequest>();
        ObjectMapper mapper = new ObjectMapper();

        try {

            for (LinkedHashMap<String, Object> map : pendingRequestMap) {

                String bloodRequestJSON = mapper.writeValueAsString(map);
                BloodRequest bloodRequest = mapper.readValue(bloodRequestJSON, BloodRequest.class);

                requestBloodList.add(bloodRequest);
            }
            return requestBloodList;
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e) {
            System.out.println("IOexception Exception");
            throw e;
        }
    }*/

}
