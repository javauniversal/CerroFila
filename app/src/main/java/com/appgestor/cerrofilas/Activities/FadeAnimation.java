package com.appgestor.cerrofilas.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.appgestor.cerrofilas.Fragments.SampleSlide;
import com.appgestor.cerrofilas.R;
import com.github.paolorotolo.appintro.AppIntro;

public class FadeAnimation extends AppIntro {

    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(SampleSlide.newInstance(R.layout.intro));
        addSlide(SampleSlide.newInstance(R.layout.intro2));

        setFadeAnimation();
    }

    private void loadMainActivity(){
        startActivity(new Intent(this, ActLogin.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
        //Toast.makeText(getApplicationContext(), getString(R.string.skip), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    public void getStarted(View v){
        loadMainActivity();
    }
}