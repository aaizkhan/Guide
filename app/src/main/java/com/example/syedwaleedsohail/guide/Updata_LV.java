package com.example.syedwaleedsohail.guide;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Updata_LV extends AppCompatActivity {

    ListView updation_List;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    Subject1 subject1 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata__lv);

        updation_List = (ListView)findViewById(R.id.updation_list);
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Programming");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                for (final DataSnapshot ds : dataSnapshot.getChildren()){

                    subject1 = ds.getValue(Subject1.class);
                    arrayList.add(subject1.getS_heading().toUpperCase());


                }
                updation_List.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        updation List
        updation_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent in = new Intent(Updata_LV.this , Updation_ACT.class);
                in.putExtra("Heading",subject1.getS_heading());
                in.putExtra("Explaination",subject1.getS_explanation());
                in.putExtra("Example",subject1.getS_example());
                startActivity(in);



            }
        });


    }
}
