package com.example.redo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ListingsActivity extends AppCompatActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemReselectedListener {

    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        BottomNavigationView bottomNavigation =findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemReselectedListener(this);

        ListView listView = findViewById(R.id.listingsList);
        ArrayList<String> listData = new ArrayList<>();
        listData.add("something1");
        listData.add("something2");
        listData.add("something3");
        listData.add("something4");
        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(listAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                break;
        }
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                Intent mainIntent = new Intent(ListingsActivity.this, MainActivity.class);
                startActivity(mainIntent);
                break;
        }
    }
}