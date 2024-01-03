package com.example.haraka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.checkerframework.checker.nullness.qual.NonNull;

public class SignUp extends AppCompatActivity {

    EditText fName, lName, email, password, cPassword;
    Button signUp;
    TextView loginLink;

    FirebaseAuth fireAuth = FirebaseAuth.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUp = findViewById(R.id.sign_up);
        loginLink = findViewById(R.id.login_link);
        email = findViewById(R.id.email_sign_up);
        password = findViewById(R.id.pass_sign_up);
        cPassword = findViewById(R.id.confirm_pass);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailVar = String.valueOf(email.getText());
                String passwordVar = String.valueOf(password.getText());




                fireAuth.createUserWithEmailAndPassword(emailVar,passwordVar)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){

                                    fireAuth.getCurrentUser().sendEmailVerification()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()){

                                                                Toast.makeText(SignUp.this, "Your data has been Submitted Successfully", Toast.LENGTH_SHORT).show();
                                                                Intent validation =new Intent(SignUp.this, VerifyEmail.class);
                                                                startActivity(validation);
                                                                finish();
                                                            }else {
                                                                Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });


                                }
                                else {
                                    Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(SignUp.this, MainActivity.class);
                startActivity(login);
            }
        });

    }
}