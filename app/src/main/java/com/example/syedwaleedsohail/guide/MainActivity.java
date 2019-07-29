package com.example.syedwaleedsohail.guide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.security.auth.Subject;

public class MainActivity extends AppCompatActivity {


    EditText heading,explaination,example;
    Button submit;
   DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       databaseReference= FirebaseDatabase.getInstance().getReference("Programming");

        heading = (EditText) findViewById(R.id.edit_heading);
        explaination = (EditText) findViewById(R.id.edit_explaination);
        example = (EditText) findViewById(R.id.edit_example);

        submit = (Button) findViewById(R.id.btn_submitProg);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Heading , Explanation , EG;

                Heading = heading.getText().toString();
                Explanation = explaination.getText().toString();
                EG = example.getText().toString();

                if (Heading.isEmpty()){


                    heading.setError("Heading Required!");

                }
                else if (Explanation.isEmpty()){

                    explaination.setError("Explanation Required");

                }
                else if (EG.isEmpty()){

                    example.setError("Example Required!");

                }

                else{



                    Subject1 subject1 = new Subject1(Heading , Explanation , EG);
                    databaseReference.child(Heading).setValue(subject1);
                    Toast.makeText(MainActivity.this, "Data Inserted!", Toast.LENGTH_SHORT).show();
                    finish();

                }




            }
        });






    }
}
