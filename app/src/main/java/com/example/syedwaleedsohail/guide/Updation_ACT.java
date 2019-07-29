package com.example.syedwaleedsohail.guide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Updation_ACT extends AppCompatActivity {

    EditText edit_heading , edit_explaination , edit_example;
    Button btn_update;

    private String heading;
    private String explaination;
    private String example;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updation__act);


        heading = getIntent().getStringExtra("Heading");
        explaination = getIntent().getStringExtra("Explaination");
        example = getIntent().getStringExtra("Example");

        edit_heading = (EditText)findViewById(R.id.update_heading);
        edit_explaination = (EditText)findViewById(R.id.update_explaination);
        edit_example= (EditText)findViewById(R.id.update_exmple);

        btn_update = (Button) findViewById(R.id.btn_update);






        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                 Subject1 subject1 = new Subject1();
                 subject1.setS_heading(edit_heading.getText().toString());
                 subject1.setS_explanation(edit_explaination.getText().toString());
                 subject1.setS_example(edit_example.getText().toString());



            }
        });

    }
}
