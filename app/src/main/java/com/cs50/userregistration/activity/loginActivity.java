package com.cs50.userregistration.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cs50.userregistration.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    EditText email,password;
    ProgressBar bar;
    Button btn;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btn=findViewById(R.id.login);
        email=findViewById(R.id.email_login);
        password=findViewById(R.id.password_login);
        bar=findViewById(R.id.bar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emaillogin =email.getText().toString();
                String passwordlogin=password.getText().toString();
                bar.setVisibility(View.VISIBLE);
                mauth=FirebaseAuth.getInstance();

                mauth.signInWithEmailAndPassword(emaillogin,passwordlogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            bar.setVisibility(View.INVISIBLE);
                            Toast.makeText(loginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(loginActivity.this, HomeActivity.class);
                            intent.putExtra("email",mauth.getCurrentUser().getEmail());
                            intent.putExtra("UID",mauth.getCurrentUser().getUid());
                            startActivity(intent);
                        }else{
                            email.setText("");
                            password.setText("");
                            bar.setVisibility(View.INVISIBLE);
                            Toast.makeText(loginActivity.this, "incorrect password or email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}