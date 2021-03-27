package com.example.redo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ItemActivity extends AppCompatActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private TextView nameText;
    private TextView locationText;
    private TextView priceText;
    private TextView descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // setup bottom navigation bar
        BottomNavigationView bottomNavigation = findViewById(R.id.sellBottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.menuSell);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        // setup buttons
        MaterialButton backButton = findViewById(R.id.itemBack);
        MaterialButton submitButton = findViewById(R.id.itemSubmit);
        backButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        nameText = findViewById(R.id.itemName);
        locationText = findViewById(R.id.itemLocation);
        priceText = findViewById(R.id.itemPrice);
        descriptionText = findViewById(R.id.itemDescription);

        String listingId = getIntent().getExtras().getString("listingId");
        // add listing information
        db.collection("listings").document(listingId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                Listing listing = task.getResult().toObject(Listing.class);
                nameText.setText(listing.getName());
                locationText.setText(listing.getOrganization());
                priceText.setText(listing.getPrice().toString());
                descriptionText.setText(listing.getDescription());
            }
        });
    }

    // bottom navigation button click
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuListings:
                Intent listingsIntent = new Intent(ItemActivity.this, ListingsActivity.class);
                startActivity(listingsIntent);
                break;
            case R.id.menuSell:
                Intent sellIntent = new Intent(ItemActivity.this, SellActivity.class);
                startActivity(sellIntent);
                break;
            case R.id.menuAccount:
                Intent accountIntent = new Intent(ItemActivity.this, AccountActivity.class);
                startActivity(accountIntent);
                break;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.itemBack:
                Intent mainIntent = new Intent(ItemActivity.this, MainActivity.class);
                startActivity(mainIntent);
                break;
            case R.id.itemSubmit:
                //add this offer to the database
                Intent thankyouIntent = new Intent(ItemActivity.this, ThankyouActivity.class);
                startActivity(thankyouIntent);
                break;

        }
    }
}