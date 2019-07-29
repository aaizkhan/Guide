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

public class Computing extends AppCompatActivity {

    EditText heading1 , explanation1 , example1;
    Button submit1;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computing);

       databaseReference = FirebaseDatabase.getInstance().getReference("Computing");

        submit1 = (Button) findViewById(R.id.btn_submit);
//        EditText
        heading1 = (EditText) findViewById(R.id.edit_heading1);
        explanation1 = (EditText) findViewById(R.id.edit_explaination1);
        example1 = (EditText) findViewById(R.id.edit_example1);

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Heading1 , Explaination1 ,Example1;

                Heading1      = heading1.getText().toString();
                Explaination1 = explanation1.getText().toString();
                Example1      = example1.getText().toString();

                if (Heading1.isEmpty()){


                    heading1.setError("Heading Required!");

                }
                else if (Explaination1.isEmpty()){

                    explanation1.setError("Explanation Required");

                }
                else if (Example1.isEmpty()){

                    example1.setError("Example Required!");

                }
                else {

                   Subject2 subject2 = new Subject2(Heading1,Explaination1,Example1);
                   databaseReference.child(Heading1).setValue(subject2);
                    Toast.makeText(Computing.this, "Data Inserted!", Toast.LENGTH_SHORT).show();
                    finish();


                }

            }
        });

    }
}
