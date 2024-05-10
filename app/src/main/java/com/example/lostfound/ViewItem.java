package com.example.lostfound;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

// DetailViewActivity class extending AppCompatActivity for displaying detailed item information
public class DetailViewActivity extends AppCompatActivity {

    // Declare TextView fields for displaying item details
    TextView textName, textDescription, textPhone, textLocation, textDate, textType;

    // Declare DatabaseHelper and SQLiteDatabase objects
    private DatabaseManager dbManager;
    private SQLiteDatabase dbConnection;

    // onCreate method initializing the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apply edge-to-edge configuration
        EdgeToEdge.enable(this);

        // Set the layout of this activity
        setContentView(R.layout.activity_detail_view);

        // Initialize database helper and open a writable database
        dbManager = new DatabaseManager(this);
        dbConnection = dbManager.getWritableDatabase();

        // Find TextViews by ID and assign them to variables
        textName = findViewById(R.id.textName);
        textDescription = findViewById(R.id.textDescription);
        textPhone = findViewById(R.id.textPhone);
        textLocation = findViewById(R.id.textLocation);
        textDate = findViewById(R.id.textDate);
        textType = findViewById(R.id.textType);

        // Extract data from Intent that started this activity
        Intent intent = getIntent();
        String[] itemDetails = intent.getStringArrayExtra("itemDetails");

        // Display item details in TextViews
        textName.setText("Name: " + itemDetails[1]);
        textDescription.setText("Description: " + itemDetails[2]);
        textPhone.setText("Phone: " + itemDetails[3]);
        textLocation.setText("Location: " + itemDetails[4]);
        textDate.setText("Date: " + itemDetails[5]);
        textType.setText("Type: " + itemDetails[6].toUpperCase());

        // Set OnClickListener for the delete button
        findViewById(R.id.buttonDelete).setOnClickListener(v -> {
            // Remove the item from the database using its ID
            dbConnection.delete("advertisements", "id = ?", new String[]{itemDetails[0]});

            // Notify user of successful deletion
            Toast.makeText(DetailViewActivity.this, "Item deleted successfully", Toast.LENGTH_SHORT).show();

            // Return to the previous screen
            finish();
        });
    }
}
