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

    // bottom navigation button click
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuListings:
                Intent listingsIntent = new Intent(AccountActivity.this, ListingsActivity.class);
                startActivity(listingsIntent);
                break;
            case R.id.menuSell:
                Intent sellIntent = new Intent(AccountActivity.this, SellActivity.class);
                startActivity(sellIntent);
                break;
            case R.id.menuAccount:
                Intent accountIntent = new Intent(AccountActivity.this, AccountActivity.class);
                startActivity(accountIntent);
                break;
        }
        return false;
    }

    // button click
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.accountLogout:
                mAuth.signOut();
                Intent mainIntent = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(mainIntent);
                break;

            case R.id.accountItems:
                Intent nextIntent = new Intent(AccountActivity.this, AccountActivity.class);
                startActivity(nextIntent);
        }
    }
}