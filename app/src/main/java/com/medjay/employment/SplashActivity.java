package com.medjay.employment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.medjay.employment.models.TokenManager;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences preferences;
    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(getResources().getColor(R.color.white));

        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.activity_splash);

        tokenManager=TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));

        preferences=getApplicationContext().getSharedPreferences("My_pref",MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                if (tokenManager.getToken().getToken()==null){

                    startActivity(new Intent(SplashActivity.this, ConnxionActivity.class));

                    finish();

                }else {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));

                    finish();

                }
            }
        }, 2500);
    }
}
