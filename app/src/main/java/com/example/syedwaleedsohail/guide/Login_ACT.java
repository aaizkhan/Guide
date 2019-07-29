package com.example.syedwaleedsohail.guide;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Login_ACT extends AppCompatActivity {

    EditText email , password;
    Button login;
    ProgressDialog pd;

    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__act);


        firebaseAuth = firebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {


                if (firebaseAuth.getCurrentUser() != null){

                    startActivity(new Intent(Login_ACT.this , Admin_ACT.class));
                }
            }
        };

  pd = new ProgressDialog(this);

    email = (EditText) findViewById (R.id.edit_email);
    password = (EditText) findViewById (R.id.edit_password);

    login = (Button) findViewById(R.id.btn_login);


    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            String Email    = email.getText().toString();

            String Password = password.getText().toString();

            if (Email.isEmpty()){


                email.setError("Email Required!");
            } else if (Password.isEmpty()) {

                password.setError("Password Required");
            }
            else {
                pd.setMessage("Trying to Login");
                pd.show();

                firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

//
                            Toast.makeText(Login_ACT.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        }
    });


    }
}
