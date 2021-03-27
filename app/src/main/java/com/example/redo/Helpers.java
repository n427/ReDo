package com.example.redo;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Helpers {
    public static final String TAG = "ReDo";

    public static User getUser(String id) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final CountDownLatch latch = new CountDownLatch(1);

        final List<User> users = new ArrayList<>();
        db.collection("users").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                users.add(task.getResult().toObject(User.class));
                latch.countDown();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return users.get(0);
    }

    public static Listing getListing(String id) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final CountDownLatch latch = new CountDownLatch(1);

        final List<Listing> listings = new ArrayList<>();
        db.collection("listings").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                listings.add(task.getResult().toObject(Listing.class));
                latch.countDown();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return listings.get(0);
    }
}
