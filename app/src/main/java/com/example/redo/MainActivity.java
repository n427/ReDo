package com.example.redo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        MaterialButton loginButton = findViewById(R.id.mainLoginButton);
        MaterialButton regButton = findViewById(R.id.mainRegButton);

        loginButton.setOnClickListener(this);
        regButton.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent listingsIntent = new Intent(MainActivity.this, ListingsActivity.class);
            startActivity(listingsIntent);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainLoginButton:
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.mainRegButton:
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
                break;
        }
    }

}