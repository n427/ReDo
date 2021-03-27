package com.example.redo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private EditText emailEdit;
    private EditText passwordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        MaterialButton backButton = findViewById(R.id.loginBack);
        MaterialButton loginButton = findViewById(R.id.loginLogin);

        backButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        emailEdit = findViewById(R.id.loginEmail);
        passwordEdit = findViewById(R.id.loginPassword);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginBack:
                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(mainIntent);
                break;
            case R.id.loginLogin:
                String email = emailEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Enter your email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // If sign in succeeds, go to the listings screen
                            Intent listingsIntent = new Intent(LoginActivity.this, ListingsActivity.class);
                            startActivity(listingsIntent);
                        } else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(LoginActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}