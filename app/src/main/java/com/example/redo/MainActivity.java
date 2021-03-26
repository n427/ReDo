package com.example.redo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton buyButton = findViewById(R.id.buyButton);
        MaterialButton sellButton = findViewById(R.id.sellButton);

        buyButton.setOnClickListener(this);
        sellButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buyButton:
                Intent buyIntent = new Intent(MainActivity.this, BuyActivity.class);
                startActivity(buyIntent);
                break;
            case R.id.sellButton:
                Intent sellIntent = new Intent(MainActivity.this, SellActivity.class);
                startActivity(sellIntent);
                break;
        }
    }

}