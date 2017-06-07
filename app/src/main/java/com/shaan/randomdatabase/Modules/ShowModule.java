package com.shaan.randomdatabase.Modules;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.shaan.randomdatabase.DatabaseClasses.Database;
import com.shaan.randomdatabase.R;

public class ShowModule extends Activity {

    Context context = this;
    TextView tvRowId, tvUsername, tvPassword;
    String rowIds = "*", usernames = "*Empty*", passwords = "*Empty*";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);

        tvRowId = (TextView) findViewById(R.id.tvShowRowId);
        tvUsername = (TextView) findViewById(R.id.tvShowUsername);
        tvPassword = (TextView) findViewById(R.id.tvShowPassword);

        Database db = new Database(context);

        try {
            db.open();
            String[] result = db.getData();
            if(result[0].equals("empty")) {
                Toast.makeText(getBaseContext(), "Database is Empty...", Toast.LENGTH_LONG).show();
                finish();
            }
            rowIds = result[0];
            usernames = result[1];
            passwords = result[2];
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
            finish();
        }

        tvRowId.setText(rowIds);
        tvUsername.setText(usernames);
        tvPassword.setText(passwords);
    }
}
