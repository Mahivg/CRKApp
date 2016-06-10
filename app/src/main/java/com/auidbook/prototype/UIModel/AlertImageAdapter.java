package com.auidbook.prototype.UIModel;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.ContentHandler;
import java.util.Arrays;

/**
 * Created by mgundappan on 03-06-2016.
 */
public class AlertImageAdapter extends BaseAdapter {

    private Context mContext;
    private AssetManager mAssetManager;
    private String[] imageList;

    public AlertImageAdapter(Context c) {
        mContext = c;
        mAssetManager = c.getAssets();
        setImageArray();

    }

    private void setImageArray(){

        try {
            imageList = mAssetManager.list("BloodImages");
            System.out.println(" Image Array : " + Arrays.toString(imageList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return imageList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView alertImage = new ImageView(mContext);
        InputStream is = null;

        try {
            is = mAssetManager.open("BloodImages/" + imageList[position]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap bitmap = BitmapFactory.decodeStream(is);
        alertImage.setImageBitmap(bitmap);
        return alertImage;
    }
}
