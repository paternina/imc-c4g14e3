package com.example.imcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText weight;
    private EditText height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        Button calculateBtn = findViewById(R.id.btnCalcular);
        calculateBtn.setOnClickListener(view -> calculateIMC());
    }

    public void calculateIMC() {
        String pText = weight.getText().toString();
        String sText = height.getText().toString();
        double p = parseDouble(pText);
        double s = parseDouble(sText);
        double res = p / (s * s);

        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("imcResult", String.valueOf(res));
        startActivity(intent);
    }

    double parseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch(Exception e) {
                return -1;
            }
        }
        else return 0;
    }
}