package com.example.syedwaleedsohail.guide;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

public class View_Prog extends AppCompatActivity {


    ListView LV_PROG;
    SearchView  Search_BAR;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList;

    final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    final DatabaseReference uidRef = rootRef.child("Programming").child(uid);

    ArrayAdapter<String> arrayAdapter;
    List<Subject1>sbj1;
    Subject1 subject1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__prog);
          subject1 = new Subject1();

     LV_PROG = (ListView) findViewById(R.id.lv_prog);
     Search_BAR = (SearchView) findViewById(R.id.edit_Search);


        sbj1 = new ArrayList<>();



        arrayList = new ArrayList<String>();
     arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);

//     Search View

        Search_BAR.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

     firebaseDatabase = FirebaseDatabase.getInstance();
     databaseReference = firebaseDatabase.getReference("Programming");

     databaseReference.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

             for (final DataSnapshot ds : dataSnapshot.getChildren()){

                subject1 = ds.getValue(Subject1.class);
                arrayList.add(subject1.getS_heading());

//                 LV_PROG.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                     @Override
//                     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                         subject1 = ds.getValue(Subject1.class);
//                         String h1=subject1.getS_heading().toUpperCase();
//                         String e1=subject1.getS_explanation();
//                         String ee1=subject1.getS_example();
//
//                         Intent in = new Intent(View_Prog.this , subject1_ShowACT.class);
//                         in.putExtra("head1" , h1);
//                         in.putExtra("exp1" , e1);
//                         in.putExtra("eg1" , ee1);
//                         startActivity(in);
//                     }
//                 });
             }
          LV_PROG.setAdapter(arrayAdapter);
         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });


// Deletion Code

        LV_PROG.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                 delete_Programming(subject1.getS_heading());
                   return true;
            }
        });

//        Intent Act




                LV_PROG.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

               final String value = (String)adapterView.getItemAtPosition(position);

              //  System.out.println("Aizaz ahmad "+value);

                final DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Programming");

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                       final String heading=dataSnapshot.child(value).child("s_heading").getValue().toString();

                     final String explanation=dataSnapshot.child(value).child("s_explanation").getValue().toString();
                       final   String example=dataSnapshot.child(value).child("s_example").getValue().toString();

                       System.out.println("s_heading "+heading+" s_explanation "+explanation+ " s_examplee"+example);

                        Intent in = new Intent(View_Prog.this, subject1_ShowACT.class);

                        in.putExtra("Course",heading);
                        in.putExtra("Exp1", explanation);
                        in.putExtra("Eg1",example);
                        startActivity(in);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });






            }
        });


    }



    private void delete_Programming(String s_id) {

     DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Programming").child(s_id);
      databaseReference1.setValue(null);
      databaseReference1.removeValue();
        Toast.makeText(this, "Deleted!", Toast.LENGTH_SHORT).show();
        finish();
    }


}
