package com.auidbook.prototype.UIModel.Map;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.vision.barcode.Barcode;

import java.io.IOException;
import java.util.List;

/**
 * Created by njagadeesan on 20-05-2016.
 */
public class LatLongConvertor {

  public static LatLng getLocationFromAddress(String strAddress ,Context ctx) {

      Geocoder coder = new Geocoder(ctx);
      List<Address> address;
      LatLng latLng = null;

      try {
          address = coder.getFromLocationName(strAddress, 5);
          if (address == null) {
              return null;
          }
          Address location = address.get(0);
          location.getLatitude();
          location.getLongitude();
          System.out.println("Lattitude *** "+location.getLatitude()+"  Longitude *** "+location.getLongitude());
          latLng=new LatLng(location.getLatitude(),location.getLongitude());
      } catch (IOException e) {
          e.printStackTrace();
      }
         return latLng;
  }
}
//            p1 = new Barcode.GeoPoint((int) (location.getLatitude() * 1E6),
//                    (int) (location.getLongitude() * 1E6));