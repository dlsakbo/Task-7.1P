package com.example.lostfound;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

// LaunchActivity class extending AppCompatActivity for initial navigation
public class LaunchActivity extends AppCompatActivity {

    // onCreate method to initialize the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apply edge-to-edge display settings
        EdgeToEdge.enable(this);

        // Set the user interface layout for this Activity
        setContentView(R.layout.activity_launch);

        // OnClickListener for creating a new advertisement
        findViewById(R.id.buttonCreateAd).setOnClickListener(v -> {
            // Intent to transition to the AdvertisementCreation activity
            Intent toCreateAd = new Intent(LaunchActivity.this, AdvertisementCreationActivity.class);
            // Activate the AdvertisementCreation activity
            startActivity(toCreateAd);
        });

        // OnClickListener for viewing existing advertisements
        findViewById(R.id.buttonViewAds).setOnClickListener(v -> {
            // Intent to transition to the AdvertisementListing activity
            Intent toListAds = new Intent(LaunchActivity.this, AdvertisementListingActivity.class);
            // Activate the AdvertisementListing activity
            startActivity(toListAds);
        });
    }
}
