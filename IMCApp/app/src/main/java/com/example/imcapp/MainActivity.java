package com.example.imcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText weight;
    private EditText height;
    private EditText name;
    private EditText lastName;
    private EditText date;
    private EditText phone;
    private RadioButton male;
    private RadioButton female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // User data
        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastname);
        date = findViewById(R.id.date);
        phone = findViewById(R.id.phone);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
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
        HashMap<String, String> userData = setUserData();
        intent.putExtra("userData", userData);
        startActivity(intent);
    }

    public HashMap setUserData() {
        HashMap<String, String> userData = new HashMap<String, String>();
        userData.put("name", name.getText().toString());
        userData.put("lastName", lastName.getText().toString());
        userData.put("date", date.getText().toString());
        userData.put("phone", phone.getText().toString());
        if (male.isChecked()) {
            userData.put("gender", "Male");
        } else if(female.isChecked()) {
            userData.put("gender", "Female");
        } else {
            userData.put("gender", "notSet");
        }

        return userData;
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