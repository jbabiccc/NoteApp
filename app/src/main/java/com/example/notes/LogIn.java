package com.example.notes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    private EditText emailText;
    private EditText passwordText;
    private Button logIn;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        emailText = findViewById(R.id.edit_title);
        firebaseAuth = FirebaseAuth.getInstance();
        //  if(auth.getCurrentUser() != null)
        //{
          //  finish();
            //startActivity(new Intent(getApplicationContext(),MainMenu.class));
        //}
        passwordText = findViewById(R.id.pssword);
        logIn = findViewById(R.id.logIn);
        progressDialog = new ProgressDialog(this);
        logIn.setOnClickListener(this);
        Button signUp = findViewById(R.id.singUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this,Register.class));
            }
        });

    }
    private  void userLogin()
    {
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
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


        progressDialog.setMessage("Login User");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
             progressDialog.dismiss();
             if(task.isSuccessful())
             {  finish();
                startActivity(new Intent(getApplicationContext(),MainMenu.class));
             }
             else
                 Toast.makeText(LogIn.this,"Wrong email or password.Try again",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void onClick(View view)
    {
        if(view == logIn)
        {
            userLogin();
        }
    }
}
