package com.example.redo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton loginButton = findViewById(R.id.mainLoginButton);
        MaterialButton regButton = findViewById(R.id.mainRegButton);

        loginButton.setOnClickListener(this);
        regButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainLoginButton:
                Intent buyIntent = new Intent(MainActivity.this, ListingsActivity.class);
                startActivity(buyIntent);
                break;
            case R.id.mainRegButton:
                Intent sellIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(sellIntent);
                break;
        }
    }

}