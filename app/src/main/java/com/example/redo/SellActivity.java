package com.example.redo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SellActivity extends AppCompatActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    EditText nameEdit;
    EditText organizationEdit;
    EditText priceEdit;
    EditText descriptionEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // setup edit fields
        nameEdit = findViewById(R.id.sellItemName);
        organizationEdit = findViewById(R.id.sellOrganization);
        priceEdit = findViewById(R.id.sellPrice);
        descriptionEdit = findViewById(R.id.sellDescription);

        // setup bottom navigation bar
        BottomNavigationView bottomNavigation = findViewById(R.id.sellBottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.menuSell);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        // setup buttons
        MaterialButton backButton = findViewById(R.id.sellBack);
        MaterialButton sellButton = findViewById(R.id.sellSell);
        backButton.setOnClickListener(this);
        sellButton.setOnClickListener(this);
    }

    // bottom navigation button click
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuListings:
                Intent listingsIntent = new Intent(SellActivity.this, ListingsActivity.class);
                startActivity(listingsIntent);
                break;
            case R.id.menuSell:
                Intent sellIntent = new Intent(SellActivity.this, SellActivity.class);
                startActivity(sellIntent);
                break;
            case R.id.menuAccount:
                Intent accountIntent = new Intent(SellActivity.this, AccountActivity.class);
                startActivity(accountIntent);
                break;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sellBack:
                Intent backIntent = new Intent(SellActivity.this, MainActivity.class);
                startActivity(backIntent);
                break;
            case R.id.sellSell:
                String name = nameEdit.getText().toString();
                String organization = organizationEdit.getText().toString();
                double price = Double.parseDouble(priceEdit.getText().toString());
                String description = descriptionEdit.getText().toString();

                // save the listing
                Listing listing = new Listing(name, organization, price, description, mAuth.getUid());
                db.collection("listings").add(listing);

                // go to the listings page
                Intent nextIntent = new Intent(SellActivity.this, ListingsActivity.class);
                startActivity(nextIntent);
                break;
        }
    }
}