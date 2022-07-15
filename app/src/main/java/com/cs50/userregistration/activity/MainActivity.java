package com.cs50.userregistration.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cs50.userregistration.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
//    TextInputLayout email1,password1;
    EditText email1,password1;
    Button submit;
    ProgressBar pbar;
    TextView login;
    private FirebaseAuth mAuth;
    int err=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

       email1=findViewById(R.id.email);
       password1=findViewById(R.id.password);
       submit=findViewById(R.id.submit);
       pbar=findViewById(R.id.bar);

       login=findViewById(R.id.login);


       submit.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View view) {
               pbar.setVisibility(View.VISIBLE);
//               String email=email1.getEditText().toString().trim();
//               String password=password1.getEditText().toString().trim();

               String email=email1.getText().toString().trim();
               String password=password1.getText().toString().trim();

               if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
                   err++;
                   Toast.makeText(MainActivity.this, "Please enter both Email and password", Toast.LENGTH_SHORT).show();
               }else if(TextUtils.isEmpty(email)){
                   err++;
                   Toast.makeText(MainActivity.this, "Please enter the email", Toast.LENGTH_SHORT).show();

               }else if(TextUtils.isEmpty(password)){
                   err++;
                   Toast.makeText(MainActivity.this, "Please enter the password", Toast.LENGTH_SHORT).show();
               }
/*
               final String pattern=" ^(?=.*[0-9])(?=.*[a-z])(?=.*[a-z]).{8}$";

            if(pattern.matches(password)){
            }else{
                err++;
                Toast.makeText(MainActivity.this, "Please Enter the password with one digit,lower and uppercase, atleast 8 words", Toast.LENGTH_SHORT).show();
            }
*/
               mAuth=FirebaseAuth.getInstance();
               mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful() && err<1){
                           pbar.setVisibility(View.INVISIBLE);
//                           email1.getEditText().setText("");
//                           password1.getEditText().setText("");

                           email1.setText("");
                           password1.setText("");
                           Toast.makeText(MainActivity.this, "register Successfull", Toast.LENGTH_SHORT).show();
                       }else{
                           pbar.setVisibility(View.INVISIBLE);
//                           email1.getEditText().setText("");
//                           password1.getEditText().setText("");

                           email1.setText("");
                           password1.setText("");
                           Toast.makeText(MainActivity.this, "register UnSuccessfull Please try again", Toast.LENGTH_SHORT).show();
                       }
                   }
               });

           }
       });


       //for login sending to login activity
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, loginActivity.class));
            }
        });

    }


    public void viewdashboard(View view) {

        startActivity(new Intent(MainActivity.this, Dashboard.class));
    }
}