package com.example.redo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListingsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {
    FirebaseFirestore db;
    private List<Listing> listings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        db = FirebaseFirestore.getInstance();

        // setup bottom navigation bar
        BottomNavigationView bottomNavigation = findViewById(R.id.listingsBottomNavigation);
        bottomNavigation.setSelectedItemId(R.id.menuListings);
        bottomNavigation.setOnNavigationItemSelectedListener(this);

        // setup listings list
        listings = new ArrayList<>();
        ListView listView = findViewById(R.id.listingsList);
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(this);

        // add listings to listings list
        db.collection("listings").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                    Listing listing = documentSnapshot.toObject(Listing.class);
                    listing.setId(documentSnapshot.getId());
                    listings.add(listing);
                    listAdapter.add(listing.getName());
                }
            }
        });
    }

    // bottom navigation button click
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuListings:
                Intent listingsIntent = new Intent(ListingsActivity.this, ListingsActivity.class);
                startActivity(listingsIntent);
                break;
            case R.id.menuSell:
                Intent sellIntent = new Intent(ListingsActivity.this, SellActivity.class);
                startActivity(sellIntent);
                break;
            case R.id.menuAccount:
                Intent accountIntent = new Intent(ListingsActivity.this, AccountActivity.class);
                startActivity(accountIntent);
                break;
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long v) {
        Listing listing = listings.get(position);
        Intent intent = new Intent(ListingsActivity.this, ItemActivity.class);
        intent.putExtra("listingId", listing.getId());
        startActivity(intent);
    }
}