package com.auidbook.prototype.Model.DataStorage;

import android.content.Context;


import com.auidbook.prototype.Model.Donor;
import com.auidbook.prototype.Model.DonorHelper;
import com.auidbook.prototype.UIModel.Map.MapViewFragment;
import com.google.android.gms.maps.model.Marker;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by njagadeesan on 14-05-2016.
 */
public class DataStore {

    private static DataStore storeInstance = null;
    private static List<Donor> donorArrayList = null;
    private static List<String> phoneNumbers = null;
    private static Context _context;
    private static DonorHelper donorHelper = new DonorHelper();
    private HashMap<Marker, Donor> markerPointer;
    private MapViewFragment mapViewFragment;

    private DataStore() {
    }

    public static DataStore getDataStore(Context ctx) {
        if (storeInstance == null) {
            donorArrayList = donorHelper.getAllDonor() ;
            storeInstance = new DataStore();
            _context = ctx;
        }
        return storeInstance;
    }

    public static String[] getBloodArray(){

        String[] bloodNames = {"A-ve","A+ve","AB-ve","AB+ve","B-ve","B+ve","O-ve","O+ve"};

        return bloodNames;
    }

    public void setUpEventSpots() {
        mapViewFragment = new MapViewFragment();

        markerPointer = new HashMap<Marker, Donor>();
        System.out.println("in Set Up Events Spots  ______");
        //generate Lattitude And longitude From Address
        for (Donor donor : donorArrayList) {
            Marker marker = mapViewFragment.placeMarker(donor);
            markerPointer.put(marker, donor);
        }
        System.out.println("Map Frag,ment Reference  ###########  " + mapViewFragment);

    }

    public InputStream getBloodImageInputStream(String bloodGroup) {

        InputStream is = null;
        String[] bloodNames = getBloodArray();
        String[] imageList = getBloodImageList();

        for (int i = 0; i < getBloodArray().length; i++) {

            if (bloodNames[i].toLowerCase().equals(bloodGroup.toLowerCase())) {

                try {
                    is = _context.getAssets().open("BloodImages/" + imageList[i]);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    return is;
                }
            }
        }

        return is;
    }

    public String[] getBloodImageList(){

        String[] imageList = null;
        try {
            imageList =  _context.getAssets().list("BloodImages");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageList;
    }


}
