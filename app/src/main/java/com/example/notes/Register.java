package com.example.notes;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText textEmail;
    private EditText textPassword;
    private Button buttonRegister;
    private TextView already;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        setContentView(R.layout.register);
        textEmail = findViewById(R.id.regEmail);
        textPassword = findViewById(R.id.regPassword);
        already = findViewById(R.id.already);
        buttonRegister = findViewById(R.id.register);


        buttonRegister.setOnClickListener(this);
        already.setOnClickListener(this);
    }

    public void registerUser()
    {
        String email = textEmail.getText().toString().trim();
        String password = textPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }


        progressDialog.setMessage("Registering User");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(),LogIn.class));
                }
                else
                {
                    Toast.makeText(Register.this,"Could not registered.Try again",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public void onClick(View view) {
        if (view == buttonRegister) {
            registerUser();


        }
        if (view == already) {
            startActivity(new Intent(Register.this, LogIn.class));
        }
    }
}
