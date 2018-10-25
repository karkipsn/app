package com.example.colors2web.zummix_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.colors2web.zummix_app.Activities.LoginActivity;
import com.example.colors2web.zummix_app.Activities.Navigation.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView =  findViewById(R.id.imageView);
        Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        imageView.startAnimation(myAnim);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
        email =preferences.getString("email","");
        password=preferences.getString("password","");
        String type = preferences.getString("group_type","");

        Integer em = email.length();
        Integer pw = password.length();
        Integer t = type.length();


        if( email!=null &&em >0 && password !=null && pw >0 && type !=null && t >0){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }




    }

}
