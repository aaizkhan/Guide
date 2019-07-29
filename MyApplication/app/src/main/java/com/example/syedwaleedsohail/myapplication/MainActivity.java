package com.example.syedwaleedsohail.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_volley,btn_sharedprefrences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_volley=(Button)findViewById(R.id.btn_volley);
        btn_sharedprefrences=(Button)findViewById(R.id.btn_sharedprefrences);



        btn_volley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this , volley.class ));

            }
        });

        btn_sharedprefrences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(MainActivity.this , sharedprefrences.class ));
            }
        });

    }
}
