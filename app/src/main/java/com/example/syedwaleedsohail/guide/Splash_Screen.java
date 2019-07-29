package com.example.syedwaleedsohail.guide;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.syedwaleedsohail.guide.R.*;
public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent in = new Intent(Splash_Screen.this , starting_ACT.class);
                startActivity(in);

            }
        },2000);

    }
}
