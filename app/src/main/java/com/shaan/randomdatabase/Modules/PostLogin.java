package com.shaan.randomdatabase.Modules;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shaan.randomdatabase.R;

public class PostLogin extends Activity implements View.OnClickListener {

    Button bUpdate, bDelete;
    TextView tvPostLogin;
    private String username, password;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_login);

        bUpdate = (Button) findViewById(R.id.bUpdate);
        bUpdate.setOnClickListener(this);
        bDelete = (Button) findViewById(R.id.bDelete);
        bDelete.setOnClickListener(this);
        tvPostLogin = (TextView) findViewById(R.id.tvPostLogin);

        bundle = new Bundle();
        bundle = getIntent().getExtras();
        username = bundle.getString("username");
        password = bundle.getString("password");

        tvPostLogin.setText("Welcome "+ username + " ( " + password + " )");

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()) {
            case R.id.bUpdate:
                intent = new Intent("com.shaan.randomdatabase.Modules.UpdateModule");
                break;
            case R.id.bDelete:
                intent = new Intent("com.shaan.randomdatabase.Modules.DeleteModule");
                break;
        }
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
