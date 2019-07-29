package com.example.syedwaleedsohail.guide;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Admin_ACT extends AppCompatActivity {

    Button Add,View_Data,update_data,logout;
    AlertDialog.Builder builder;
    ProgressDialog pd;
    FirebaseAuth.AuthStateListener authStateListener;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__act);

        Add = (Button) findViewById (R.id.btn_add);
        View_Data = (Button) findViewById (R.id.btn_View);
        update_data = (Button) findViewById (R.id.btn_update);
        logout = (Button) findViewById (R.id.btn_logout);
        pd = new ProgressDialog(this);
        setUpFirebase();

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder = new AlertDialog.Builder(Admin_ACT.this);
                builder.setTitle("Option");
                builder.setMessage("Choose Subject");

                builder.setNegativeButton("Programming", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        startActivity(new Intent(Admin_ACT.this , MainActivity.class));

                    }
                });
                builder.setPositiveButton("Computing", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        startActivity(new Intent(Admin_ACT.this , Computing.class));
                    }
                });


                builder.show();




            }
        });

//        Logout

        logout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: attempting to sign out the user");
                FirebaseAuth.getInstance().signOut();


            }
        });

//        View

       View_Data.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               builder = new AlertDialog.Builder(Admin_ACT.this);
               builder.setTitle("Options");
               builder.setMessage("Choose Subject");

               builder.setNegativeButton("Programming", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                       startActivity(new Intent(Admin_ACT.this , View_Prog.class));
                   }
               });
               builder.setPositiveButton("Computing", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                       startActivity(new Intent(Admin_ACT.this , View_Computing.class));

                   }
               });

               builder.show();

           }
       });

       update_data.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               startActivity(new Intent(Admin_ACT.this , Updata_LV.class));
           }
       });


}
//    onStart

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
    };

    //    onStop


    @Override
    protected void onStop() {
        super.onStop();
    if (authStateListener != null){
        FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
     }
    };

    protected void setUpFirebase(){

        Log.d(TAG, "Setting authListener");
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){

                    Log.d(TAG,"onAuthstatechanged signin;" + user.getUid());
                }
                else{
                    Log.d(TAG,"onAuthstatechanged sign out;");
                    Toast.makeText(Admin_ACT.this, "Signed out", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(Admin_ACT.this , Login_ACT.class);
                    in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(in);
                }
            }
        };


    };

    @Override
    public void onBackPressed() {


        super.onBackPressed();
    }
}
