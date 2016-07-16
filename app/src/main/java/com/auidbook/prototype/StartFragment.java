package com.auidbook.prototype;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.auidbook.prototype.Model.CRKApp;
import com.auidbook.prototype.Model.Donor;
import com.auidbook.prototype.UIModel.PagerAdapter;
import com.auidbook.prototype.UIModel.PlayGifView;

import java.util.List;

/**
 * Created by mgundappan on 15-06-2016.
 */
public class StartFragment extends Fragment {

    PlayGifView playGifViewGif;
    ImageView img_green_tick;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private CRKApp crkApp;
    private Donor donorLogged;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        crkApp = (CRKApp) getActivity().getApplicationContext();
        donorLogged = crkApp.getDonor();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if(donorLogged.isRequestAccepted()){
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
        else{
            View v = inflater.inflate(R.layout.fragment_start, container, false);

            tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
            tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
            tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            viewPager = (ViewPager) v.findViewById(R.id.pager);
            adapter = new PagerAdapter(getFragmentManager(),tabLayout.getTabCount());
            viewPager.setAdapter(adapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

            return v;

        }

        // Inflate the layout for this fragment


    }

    // TODO: Rename method, update argument and hook method into UI event


}
