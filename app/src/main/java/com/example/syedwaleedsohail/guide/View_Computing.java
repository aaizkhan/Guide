package com.example.syedwaleedsohail.guide;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class View_Computing extends AppCompatActivity {


    SearchView searchView;
    ListView LV_COMPUTING;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    Subject2 subject2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__computing);

        LV_COMPUTING = (ListView)findViewById(R.id.lv_Computing);
        searchView = (SearchView)findViewById(R.id.edit_Search1);
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Computing");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    subject2 = ds.getValue(Subject2.class);
                    arrayList.add(subject2.getHeading1());

                }
                LV_COMPUTING.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        Search view

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newtext) {
                arrayAdapter.getFilter().filter(newtext);
                return false;
            }
        });

//Deletion

        LV_COMPUTING.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {


                delete_Computing(subject2.getHeading1());
                return true;
            }
        });

//      item Showing data

       LV_COMPUTING.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

               final String comp = (String) adapterView.getItemAtPosition(position);

               final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Computing");

               ref.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {


                       final String heading1= dataSnapshot.child(comp).child("heading1").getValue().toString();




                       final String explanation1 = dataSnapshot.child(comp).child("explaination1").getValue().toString();
                       final String example1= dataSnapshot.child(comp).child("example1").getValue().toString();


                       System.out.println("s_heading " + heading1+ " s_explanation " + explanation1 + " s_examplee" + example1);

                       Intent in = new Intent(View_Computing.this, Subject2_show.class);

                       in.putExtra("Course", heading1);
                       in.putExtra("Exp1", explanation1);
                       in.putExtra("Eg1", example1);
                       startActivity(in);


                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {


                   }
               });



           }
       });


    }

           private void delete_Computing(String getid) {


               DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Computing").child(getid);
               databaseReference1.setValue(null);
               databaseReference1.removeValue();
               Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
               finish();
           }
       }
