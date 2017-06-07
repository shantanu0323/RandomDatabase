package com.shaan.randomdatabase.Modules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
public class LoginModule extends Activity implements View.OnClickListener {

    Button btnLogin;
    EditText etUsername, etPassword;
    String username, password;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etUsername = (EditText) findViewById(R.id.etLoginUsername);
        etPassword = (EditText) findViewById(R.id.etLoginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getBaseContext(),"Please Wait ...", Toast.LENGTH_SHORT).show();
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        Database db = new Database(ctx);
        Cursor cursor = null;
        try {
            db.open();
            cursor = db.getInfo();
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "1. " + e.toString(), Toast.LENGTH_LONG).show();
        }

        boolean loginStatus = false;
        String NAME = "";
        int iUsername = 0;
        int iPassword = 0;
        try {
            iUsername = cursor.getColumnIndex(Database.USERNAME);
            iPassword = cursor.getColumnIndex(Database.PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "2. " + e.toString(), Toast.LENGTH_LONG).show();
        }

        boolean cond = false;
        try {
            cursor.moveToFirst();
            do {
                try {
                    cond = (username.equals(cursor.getString(iUsername))) && (password.equals(cursor.getString(iPassword)));
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getBaseContext(), "3. " + e.toString(), Toast.LENGTH_LONG).show();
                }
                if(cond) {
                    loginStatus = true;
                    NAME = cursor.getString(iUsername);
                    break;
                }
            } while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), "5. " + e.toString(), Toast.LENGTH_LONG).show();
        }
        if(loginStatus){
            Toast.makeText(getBaseContext(),"You have successfully Logged in\nWelcome "+NAME, Toast.LENGTH_LONG).show();
            Intent intent = new Intent("com.shaan.randomdatabase.Modules.PostLogin");
            Bundle bundle = new Bundle();
            bundle.putString("username",username);
            bundle.putString("password",password);
            intent.putExtras(bundle);
            try {
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getBaseContext(), "4. " + e.toString(), Toast.LENGTH_LONG).show();
            }
            finish();
        } else {
            Toast.makeText(getBaseContext(),"Username and Password Not in Database \nPlease Try Again...", Toast.LENGTH_LONG).show();
            etUsername.setText("");
            etPassword.setText("");
        }
    }
}











/*
@Override
    public void onClick(View v) {
        Toast.makeText(getBaseContext(),"Please Wait ...", Toast.LENGTH_LONG).show();
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        DatabaseHelper dh = new DatabaseHelper(ctx);
        Cursor cursor = dh.getInformation(dh);
        cursor.moveToFirst();
        boolean loginStatus = false;
        String NAME = "";
        try {
            do {
                Toast.makeText(getBaseContext(), "Text:", Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(), cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2), Toast.LENGTH_LONG).show();
                if((username.equals(cursor.getString(0))) && (password.equals(cursor.getString(1)))) {
                    Toast.makeText(getBaseContext(), "Entered Condition", Toast.LENGTH_LONG).show();
                    loginStatus = true;
                    NAME = cursor.getString(0);
                    break;
                }
            } while (cursor.moveToNext());
        } catch (Exception e) {
            if (loginStatus) {
                Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        }
        if(loginStatus){
            Toast.makeText(getBaseContext(),"You have successfully Logged in\nWelcome "+NAME, Toast.LENGTH_LONG).show();
            Intent intent = new Intent("com.shaan.randomdatabase.Modules.PostLogin");
            Bundle bundle = new Bundle();
            bundle.putString("username",username);
            bundle.putString("password",password);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getBaseContext(),"Username and Password Not in Database \nPlease Try Again...", Toast.LENGTH_LONG).show();
            etUsername.setText("");
            etPassword.setText("");
        }
    }
}

 */