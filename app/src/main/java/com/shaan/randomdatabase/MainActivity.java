package com.shaan.randomdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button bLogin, bSignUp, bShow, bClear;

    private void init() {
        bLogin = (Button) findViewById(R.id.bLogin);
        bSignUp = (Button) findViewById(R.id.bSignUp);
        bShow = (Button) findViewById(R.id.bShow);
        bClear = (Button) findViewById(R.id.bClear);
    }

    private void addListeners() {
        bLogin.setOnClickListener(this);
        bSignUp.setOnClickListener(this);
        bShow.setOnClickListener(this);
        bClear.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        addListeners();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()) {
            case R.id.bLogin:
                intent = new Intent("com.shaan.randomdatabase.Modules.LoginModule");
                break;
            case R.id.bSignUp:
                intent = new Intent("com.shaan.randomdatabase.Modules.SignUpModule");
                break;
            case R.id.bShow:
                intent = new Intent("com.shaan.randomdatabase.Modules.ShowModule");
                break;
            case R.id.bClear:
                try {
                    intent = new Intent("com.shaan.randomdatabase.Modules.ClearDatabase");
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
                break;
        }
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
