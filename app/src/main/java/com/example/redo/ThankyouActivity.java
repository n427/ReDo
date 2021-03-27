package com.example.redo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ThankyouActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);

        // setup bottom navigation bar
        BottomNavigationView bottomNavigation = findViewById(R.id.thankyouBottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.menuListings);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuListings:
                break;
            case R.id.menuSell:
                Intent sellIntent = new Intent(ThankyouActivity.this, SellActivity.class);
                startActivity(sellIntent);
                break;
            case R.id.menuAccount:
                FirebaseAuth.getInstance().signOut();
                Intent mainIntent = new Intent(ThankyouActivity.this, AccountActivity.class);
                startActivity(mainIntent);
                break;
        }
        return false;
    }
}