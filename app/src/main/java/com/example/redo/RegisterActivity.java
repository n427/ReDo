package com.example.redo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference database;
    private FirebaseAuth mAuth;

    private EditText emailEdit;
    private EditText passwordEdit;
    private EditText nameEdit;
    private EditText cityEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        database = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        MaterialButton backButton = findViewById(R.id.regBack);
        MaterialButton registerButton = findViewById(R.id.regRegister);

        backButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        emailEdit = findViewById(R.id.regEmail);
        passwordEdit = findViewById(R.id.regPassword);
        nameEdit = findViewById(R.id.regName);
        cityEdit = findViewById(R.id.regCity);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.regBack:
                Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(mainIntent);
                break;
            case R.id.regRegister:
                final String email = emailEdit.getText().toString();
                final String password = passwordEdit.getText().toString();
                final String name = nameEdit.getText().toString();
                final String city = cityEdit.getText().toString();

                if (email.isEmpty() || password.isEmpty() || name.isEmpty() || city.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // create user with email and password
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // save the user's information
                            String id = task.getResult().getUser().getUid();
                            User user = new User(name, email, city);
                            database.child("users").child(id).setValue(user);

                            // go to the listings screen
                            Intent listingsIntent = new Intent(RegisterActivity.this, ListingsActivity.class);
                            startActivity(listingsIntent);
                        } else {
                            // display a message to the user
                            Log.w(Helpers.TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}