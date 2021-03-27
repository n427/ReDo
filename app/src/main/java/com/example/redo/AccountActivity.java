package com.example.redo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth = FirebaseAuth.getInstance();

        // setup bottom navigation bar
        BottomNavigationView bottomNavigation = findViewById(R.id.accountBottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.menuAccount);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        // setup buttons
        MaterialButton logoutButton = findViewById(R.id.accountLogout);
        logoutButton.setOnClickListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuListings:
                Intent accountIntent = new Intent(AccountActivity.this, ListingsActivity.class);
                startActivity(accountIntent);
                break;
            case R.id.menuSell:
                Intent sellIntent = new Intent(AccountActivity.this, SellActivity.class);
                startActivity(sellIntent);
                break;
            case R.id.menuAccount:
                break;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.accountLogout:
                mAuth.signOut();
                Intent mainIntent = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(mainIntent);
                break;
        }
    }
}