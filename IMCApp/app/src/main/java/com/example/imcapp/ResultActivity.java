package com.example.imcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
                result.setText(getString(R.string.result) + ": "+ imc);
            }
            if(userData != null) {
                String data = "";
                data += "Nombres: " + userData.get("name") + "\n";
                data += "Apellidos: " + userData.get("lastName") + "\n";
                data += "Teléfono: " + userData.get("phone") + "\n";
                data += "Fecha Nacimiento: " + userData.get("date") + "\n";
                data += "Género: " + userData.get("gender") + "\n";
                userInfo.setText(data);
            }
        }

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}