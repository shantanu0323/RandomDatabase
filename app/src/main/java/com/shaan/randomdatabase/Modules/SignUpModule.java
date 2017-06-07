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
public class SignUpModule extends Activity implements View.OnClickListener {

    EditText etUsername, etPassword, etCnfPassword;
    String username, password, cnfPassword;
    Button btnSignUp;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        etUsername = (EditText) findViewById(R.id.etSignUpUsername);
        etPassword = (EditText) findViewById(R.id.etSignUpPassword);
        etCnfPassword = (EditText) findViewById(R.id.etSignUpCnfPassword);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        username = etUsername.getText().toString();
        password = etPassword.getText().toString();
        cnfPassword = etCnfPassword.getText().toString();

        if(!(password.equals(cnfPassword))) {
            Toast.makeText(getBaseContext(),"Passwords do not match... Please Try Again...", Toast.LENGTH_LONG).show();
            etPassword.setText("");
            etCnfPassword.setText("");
        } else {
            Database db = new Database(ctx);
            try {
                db.open();
                db.putInfo(username, password);
                db.close();
            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
            Toast.makeText(getBaseContext(), "Sign Up Successful", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}