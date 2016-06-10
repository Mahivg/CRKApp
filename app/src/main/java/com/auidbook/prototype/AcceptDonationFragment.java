package com.auidbook.prototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.auidbook.prototype.UIModel.PlayGifView;

/**
 * Created by mgundappan on 03-06-2016.
 */
public class AcceptDonationFragment extends Fragment {

    PlayGifView playGifViewGif;

    ImageView img_green_tick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_after_accept_donation, container, false);

        /*playGifViewGif= (PlayGifView)v.findViewById(R.id.play_gifview);
        playGifViewGif.setImageResource(R.raw.blood_donor_contact_gif);*/

        img_green_tick  = (ImageView) v.findViewById(R.id.img_green_tick);

        /*TranslateAnimation animation = new TranslateAnimation(0.0f, 400.0f,
                0.0f, 0.0f);
        animation.setDuration(5000);
        animation.setRepeatCount(5);
        animation.setRepeatMode(2);
        animation.setFillAfter(true);
        img_green_tick.startAnimation(animation);*/

        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(5000);
        rotateAnimation.setRepeatCount(5);
        rotateAnimation.setRepeatMode(2);
        rotateAnimation.setFillAfter(true);
        img_green_tick.startAnimation(rotateAnimation);
        // Inflate the layout for this fragment


        return v;
    }
}
