package com.example.haraka;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class VerifyEmail extends AppCompatActivity {
    TextView emailMsg;
    FirebaseAuth fireAuth = FirebaseAuth.getInstance();
    Handler handler = new Handler();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);

        emailMsg = findViewById(R.id.verify_email);
        String currentUser = fireAuth.getCurrentUser().getEmail().toString();

        emailMsg.setText(currentUser);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(VerifyEmail.this, MainActivity.class));
            }
        }, 8000);

    }
}