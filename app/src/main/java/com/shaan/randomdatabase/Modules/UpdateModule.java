package com.shaan.randomdatabase.Modules;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shaan.randomdatabase.DatabaseClasses.Database;
import com.shaan.randomdatabase.R;

/**
 * Created by SHAAN on 01-02-17.
 */
public class UpdateModule extends Activity implements View.OnClickListener {

    Button btnUpdate;
    EditText etUsername, etPassword;
    Context ctx = this;
    String oldUser, oldPass, newUser, newPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        Bundle bundle = getIntent().getExtras();
        oldUser = bundle.getString("username");
        oldPass = bundle.getString("password");
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);

        etUsername = (EditText) findViewById(R.id.etUpdateUsername);
        etUsername.setText(oldUser);

        etPassword = (EditText) findViewById(R.id.etUpdatePassword);
        etPassword.setText(oldPass);

    }

    @Override
    public void onClick(View v) {
        newUser = etUsername.getText().toString();
        newPass = etPassword.getText().toString();

        Database db = new Database(ctx);
        try {
            db.open();
            db.updateInfo(oldUser, newUser, oldPass, newPass);
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(),e.toString(),Toast.LENGTH_LONG).show();
            finish();
        }
        Toast.makeText(getBaseContext(), "Update Successfull", Toast.LENGTH_LONG).show();
        finish();
    }
}
