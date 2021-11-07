package com.example.imcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();
        Button goBackBtn = findViewById(R.id.go_back);

        TextView result = findViewById(R.id.imc_result);
        TextView userInfo = findViewById(R.id.user_info);
        if(extras != null) {
            String imc = extras.getString("imcResult");
            HashMap<String, String> userData = (HashMap<String, String>) extras.getSerializable("userData");
            if(imc != null) {
                result.setText(getString(R.string.result, imc));
            }
            if(userData != null) {
                String data = "";
                data += "NIT: " + userData.get("nit") + "\n";
                data += getString(R.string.name) + ": " + userData.get("name") + "\n";
                data += getString(R.string.lastname) + ": " + userData.get("lastName") + "\n";
                data += getString(R.string.phone) + ": " + userData.get("phone") + "\n";
                data += getString(R.string.birth_date) + ": " + userData.get("date") + "\n";
                data += getString(R.string.gender) + ": " + userData.get("gender") + "\n";
                userInfo.setText(data);
            }
        }

        goBackBtn.setOnClickListener(view -> finish());
    }
}
