package com.shaan.randomdatabase.Modules;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.Toast;

import com.shaan.randomdatabase.DatabaseClasses.Database;

/**
 * Created by SHAAN on 01-02-17.
 */
public class DeleteModule extends Activity {

    Context ctx = this;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        Database db = new Database(ctx);
        try {
            db.open();
            db.deleteInfo(username);
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
            finish();
        }
        Toast.makeText(getBaseContext(), "Account successfully deleted...", Toast.LENGTH_LONG).show();
        finish();
    }
}
