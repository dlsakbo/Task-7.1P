package com.example.lostfound;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

// AdvertisementActivity class extending AppCompatActivity
public class AdvertisementActivity extends AppCompatActivity {

    // Declare UI components and database-related fields
    EditText inputName, inputDescription, inputPhone, inputDate, inputLocation;
    Button saveButton;
    private DBHelper localDBHelper;
    private SQLiteDatabase localDB;
    RadioButton radioButtonLost, radioButtonFound;

    // onCreate method to initialize the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apply edge-to-edge display setting
        EdgeToEdge.enable(this);

        // Set the view layout for the activity
        setContentView(R.layout.activity_create_advert);

        // Initialize DBHelper and SQLiteDatabase
        localDBHelper = new DBHelper(this);
        localDB = localDBHelper.getWritableDatabase();

        // Link UI components
        inputName = findViewById(R.id.editTextName);
        inputDescription = findViewById(R.id.editTextDescription);
        inputPhone = findViewById(R.id.editTextPhone);
        inputDate = findViewById(R.id.editTextDate);
        inputLocation = findViewById(R.id.editTextLocation);
        saveButton = findViewById(R.id.buttonSave);
        radioButtonLost = findViewById(R.id.radioButtonLost);
        radioButtonFound = findViewById(R.id.radioButtonFound);

        // Set up the button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processAdvertData(); // Method to process and store advertisement data
            }
        });
    }

    // Method to process and add advertisement data to the database
    private void processAdvertData() {
        // Extract input data from UI components
        String name = inputName.getText().toString();
        String description = inputDescription.getText().toString();
        String phone = inputPhone.getText().toString();
        String date = inputDate.getText().toString();
        String location = inputLocation.getText().toString();
        String advertType = "";

        // Check which type of advertisement is selected
        if (radioButtonLost.isChecked()) {
            advertType = "lost";
        } else if (radioButtonFound.isChecked()) {
            advertType = "found";
        }

        // Check if any required fields are empty
        if (name.isEmpty() || description.isEmpty() || phone.isEmpty() || date.isEmpty() || location.isEmpty() || advertType.isEmpty()) {
            Toast.makeText(this, "All fields must be completed!", Toast.LENGTH_SHORT).show();
            return; // Exit if any field is missing
        }

        // Create ContentValues to facilitate data insertion
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("phone", phone);
        values.put("date", date);
        values.put("type", advertType);
        values.put("location", location);

        // Insert the new record into the database
        localDB.insert("my_table", null, values);

        // Notify user of successful operation
        Toast.makeText(this, "Advertisement posted successfully", Toast.LENGTH_SHORT).show();

        // Close the activity
        finish();
    }
}
