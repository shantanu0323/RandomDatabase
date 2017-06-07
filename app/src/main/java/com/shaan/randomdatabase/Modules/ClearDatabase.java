package com.shaan.randomdatabase.Modules;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.Toast;

import com.shaan.randomdatabase.DatabaseClasses.Database;

/**
 * Created by SHAAN on 03-02-17.
 */
public class ClearDatabase extends Activity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Database db = new Database(context);
        try {
            db.open();
            db.clearDatabase();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
            finish();
        }
        Toast.makeText(getBaseContext(), "Database successfully Cleared", Toast.LENGTH_LONG).show();
        finish();
    }
}