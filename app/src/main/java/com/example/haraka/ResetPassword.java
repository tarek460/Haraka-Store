package com.example.haraka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.checkerframework.checker.nullness.qual.NonNull;

public class ResetPassword extends AppCompatActivity {

    EditText email;
    Button reset;
    FirebaseAuth fireAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        email = findViewById(R.id.reset_email);
        reset = findViewById(R.id.reset_btn);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailVar = email.getText().toString();

                fireAuth.sendPasswordResetEmail(emailVar)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(ResetPassword.this, "Check your email", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ResetPassword.this, MainActivity.class));
                                    finish();
                                }else {
                                    Toast.makeText(ResetPassword.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });

    }
}