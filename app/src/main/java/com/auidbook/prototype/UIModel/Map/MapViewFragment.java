package com.auidbook.prototype.UIModel.Map;

import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.auidbook.prototype.Model.BloodRequest;
import com.auidbook.prototype.Model.Donor;
import com.auidbook.prototype.Model.Fields.Address;
import com.auidbook.prototype.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by njagadeesan on 18-05-2016.
 */
public class MapViewFragment extends MapFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        System.out.println("MAPVIEW  Fragment ______s");
       return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public Marker placeMarker(Donor donorInfo) {

        LatLng latLng = new LatLng(donorInfo.getLatitude(),donorInfo.getLongitude());

        Marker ownMarker = getMap().addMarker(new MarkerOptions()
                .position(latLng)
                .title("Red Knights Donars")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        return ownMarker;

    }


}


