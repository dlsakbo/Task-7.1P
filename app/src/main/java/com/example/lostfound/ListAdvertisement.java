package com.example.lostfound;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.database.Cursor;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// DisplayAdvertisements class for showing advertisements in a RecyclerView
public class DisplayAdvertisements extends AppCompatActivity {

    // Declare RecyclerView, adapter, list of data, database helper, and database
    private RecyclerView advertisementList;
    private ItemListAdapter advertisementAdapter;
    private List<String[]> advertisementData;
    private DatabaseManager databaseManager;
    private SQLiteDatabase dbConnection;

    // onCreate method initializing the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Implement edge-to-edge display
        EdgeToEdge.enable(this);

        // Set the activity layout
        setContentView(R.layout.activity_display_adverts);

        // Setup database helper and connection
        databaseManager = new DatabaseManager(this);
        dbConnection = databaseManager.getWritableDatabase();

        // Setup RecyclerView with a LinearLayoutManager
        advertisementList = findViewById(R.id.rv_advertisement_list);
        advertisementList.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the data list and adapter
        advertisementData = new ArrayList<>();
        advertisementAdapter = new ItemListAdapter(this, advertisementData);

        // Retrieve and display sorted data
        fetchDataSortedByNameDesc();
    }

    // Fetch and sort data from the database by name in descending order
    public void fetchDataSortedByNameDesc() {
        // Clear existing data to prevent duplication
        advertisementData.clear();

        // Query to fetch all records sorted by date in descending order
        Cursor dataCursor = dbConnection.query(
                "advertisements",  // Table name
                null,              // Columns to return (null for all)
                null,              // Rows to return (null for all)
                null,              // Selection arguments
                null,              // GROUP BY clause
                null,              // HAVING clause
                "date DESC"        // ORDER BY clause
        );

        // Process each row in the cursor
        if (dataCursor != null && dataCursor.moveToFirst()) {
            do {
                // Extract data from cursor
                @SuppressLint("Range") String id = String.valueOf(dataCursor.getLong(dataCursor.getColumnIndex("id")));
                @SuppressLint("Range") String name = dataCursor.getString(dataCursor.getColumnIndex("name"));
                @SuppressLint("Range") String description = dataCursor.getString(dataCursor.getColumnIndex("description"));
                @SuppressLint("Range") String date = dataCursor.getString(dataCursor.getColumnIndex("date"));
                @SuppressLint("Range") String phone = dataCursor.getString(dataCursor.getColumnIndex("phone"));
                @SuppressLint("Range") String location = dataCursor.getString(dataCursor.getColumnIndex("location"));
                @SuppressLint("Range") String type = dataCursor.getString(dataCursor.getColumnIndex("type"));

                // Add data to list as an array
                advertisementData.add(new String[]{id, name, description, phone, location, date, type});
            } while (dataCursor.moveToNext());
            dataCursor.close(); // Close cursor after use
        }

        // Set the adapter for the RecyclerView
        advertisementList.setAdapter(advertisementAdapter);
    }
}
