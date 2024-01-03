package com.example.haraka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;
import java.util.regex.Pattern;

import javax.xml.validation.Validator;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText email, password;
    TextView forgot, signUpLink;
    String templag;
    FirebaseAuth fireAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        signUpLink = findViewById(R.id.sign_up_link);
        forgot = findViewById(R.id.forgot);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        templag = Locale.getDefault().getLanguage();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailVar = email.getText().toString();
                String passwordVar = password.getText().toString();
                if (Patterns.EMAIL_ADDRESS.matcher(emailVar).matches() && !emailVar.isEmpty()){
                    password.requestFocus();
                } else {
                    if (templag == "ar") {
                        email.setError("بريد إلكتروني غير صحيح");
                        email.requestFocus();
                        return;
                    }
                    else {
                        email.setError("Invalid Email address");
                        email.requestFocus();
                        return;
                    }
                }

                if (passwordVar.isEmpty()){
                    if (templag == "ar") {
                        password.setError("يجب إدخال كلمة المرور");
                        password.requestFocus();
                        return;
                    }
                    else {
                        password.setError("Password is required");
                        password.requestFocus();
                        return;
                    }
                }

                fireAuth.signInWithEmailAndPassword(emailVar,passwordVar)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {


                                    if (task.isSuccessful()){
                                        if (fireAuth.getCurrentUser().isEmailVerified()){
                                            Toast.makeText(MainActivity.this, "Welcome to Haraka market", Toast.LENGTH_SHORT).show();
                                            Intent home = new Intent(MainActivity.this, Home.class);
                                            startActivity(home);
                                            finish();
                                        }else {
                                            Toast.makeText(MainActivity.this, "Please verify your email first", Toast.LENGTH_SHORT).show();
                                        }


                                    }else {
                                        Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                                    }
                            }
                        });


            }
        });

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(MainActivity.this, SignUp.class);
                startActivity(signup);
            }
        });


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reset = new Intent(MainActivity.this, ResetPassword.class);
                startActivity(reset);


            }
        });
    }

    @Override
    protected void onStart() {
        //if (fireAuth.getCurrentUser() == null){
          //  startActivity(new Intent(MainActivity.this, MainActivity.class));
      //  }else {
           // startActivity(new Intent(MainActivity.this, Home.class));
        //}-->
        super.onStart();
    }
}