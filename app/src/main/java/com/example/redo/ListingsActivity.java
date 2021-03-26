package com.example.redo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListingsActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

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

        }
    }
}