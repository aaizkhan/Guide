package com.example.syedwaleedsohail.guide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Subject2_show extends AppCompatActivity {

    TextView t1,t2,t3;

    private String coursename;
    private String Heading1;
    private String Explaination1;
    private String Example1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject2_show);

        t1 = (TextView)findViewById(R.id.th2);
        t2 = (TextView)findViewById(R.id.te2);
        t3 = (TextView)findViewById(R.id.tee2);

        coursename= getIntent().getExtras().get("Course").toString();
        Toast.makeText(this, coursename, Toast.LENGTH_SHORT).show();
        Explaination1 = getIntent().getExtras().get("Exp1").toString();
        Example1 = getIntent().getExtras().get("Eg1").toString();
        t1.setText(coursename);
        t2.setText(Explaination1);
        t3.setText(Example1);



    }
}
