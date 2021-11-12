package com.example.imcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IMCActivity extends AppCompatActivity {
    private EditText weight, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);

        Button calculateBtn = findViewById(R.id.calculate_btn);

        calculateBtn.setOnClickListener(view -> {
            double imc = calculateIMC();
            weight.setText("");
            height.setText("");
            CharSequence text = "IMC Result: " + imc;
            // Show a toast message with the information
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            finish();
        });
    }

    public double calculateIMC() {
        String pText = weight.getText().toString();
        String sText = height.getText().toString();
        double p = parseDouble(pText);
        double s = parseDouble(sText);
        // Return IMC Result
        return p / (s * s);
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
