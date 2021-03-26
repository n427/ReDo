package com.example.redo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class SellActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nameEdit;
    EditText priceEdit;
    EditText descriptionEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        nameEdit = findViewById(R.id.sellItemName);
        priceEdit = findViewById(R.id.sellPrice);
        descriptionEdit = findViewById(R.id.sellDescription);

        MaterialButton backButton = findViewById(R.id.sellBack);
        MaterialButton submitButton = findViewById(R.id.sellSubmit);

        backButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sellBack:
                Intent backIntent = new Intent(SellActivity.this, MainActivity.class);
                startActivity(backIntent);
                break;
            case R.id.sellSubmit:
                submit();
                Intent nextIntent = new Intent(SellActivity.this, SellActivity.class);
                startActivity(nextIntent);
                break;
        }
    }

    private void submit() {
        // get values from the input fields
        String name = nameEdit.getText().toString();
        int price = Integer.parseInt(priceEdit.getText().toString());
        String description = descriptionEdit.getText().toString();

        // convert values into json format
        JSONObject listing = new JSONObject();

        try {
            listing.put("name", name);
            listing.put("price", price);
            listing.put("description", description);
            listing.put("sellerId", UUID.randomUUID()); // TODO: change this to the seller id
            listing.put("date", new SimpleDateFormat("MM-dd-yyyy").format(new Date()));
        } catch (JSONException ignored) {
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child("postings").child(UUID.randomUUID().toString()).setValue(listing);
    }
}