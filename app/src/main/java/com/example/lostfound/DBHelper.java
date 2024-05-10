package com.example.lostfound;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// DatabaseManager class extending SQLiteOpenHelper for handling database operations
public class DatabaseManager extends SQLiteOpenHelper {

    // Constants for database name and version
    private static final String DB_NAME = "lostFoundDB.db";
    private static final int DB_VERSION = 1;

    // Constructor for DatabaseManager
    public DatabaseManager(Context context) {
        // Initialize the database with the specified name and version
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Method called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase database) {
        // SQL command to construct the "advertisements" table
        String CREATE_TABLE_SQL =
                "CREATE TABLE advertisements (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT, " +
                        "phone TEXT, " +
                        "description TEXT, " +
                        "location TEXT, " +
                        "advert_type TEXT, " +
                        "date TEXT)";

        // Execute the SQL statement to create the table
        database.execSQL(CREATE_TABLE_SQL);
    }

    // Method called when the database needs an upgrade
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // Remove the existing "advertisements" table if it exists
        database.execSQL("DROP TABLE IF EXISTS advertisements");
        // Rebuild the database by invoking onCreate
        onCreate(database);
    }
}
