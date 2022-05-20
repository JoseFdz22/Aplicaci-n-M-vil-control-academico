package com.example.rendirse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class splash extends AppCompatActivity {
ImageView fondo,control;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        lottieAnimationView = findViewById(R.id.lottie);

        lottieAnimationView.animate().translationY(10).setDuration(1000).setStartDelay(4000);

new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {
        startActivity(new Intent(splash.this, sesion.class));
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        finish();
    }
},5000);
    }
}