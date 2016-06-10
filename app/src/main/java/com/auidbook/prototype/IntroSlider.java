package com.auidbook.prototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;

/**
 * Created by Rawoof on 4/4/2016.
 */

public class IntroSlider extends AppIntro {




    @Override
    public void init(@Nullable Bundle savedInstanceState) {

        addSlide(SampleSlide.newInstance(R.layout.intro_fragment_layout_1));
        addSlide(SampleSlide.newInstance(R.layout.intro_fragment_layout_2));
        addSlide(SampleSlide.newInstance(R.layout.intro_fragment_layout_3));
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    @Override
    public void onSlideChanged() {

    }

    public void getStarted(View v){
        loadMainActivity();
    }


}