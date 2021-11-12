package com.example.imcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.imcapp.db.IMCDbHelper;
import com.example.imcapp.db.ImcContract;

public class UserActivity extends AppCompatActivity {
    private EditText nit, name, lastName, age, phone;
    private RadioButton male, female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        nit = findViewById(R.id.nit);
        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastname);
        age = findViewById(R.id.edad);
        phone = findViewById(R.id.phone);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

//        Material Calendar
//        MaterialDatePicker.Builder<?> builder = MaterialDatePicker.Builder.datePicker();
//        builder.setTitleText("Select a date");
//        final MaterialDatePicker<?> materialDatePicker = builder.build();
//        Load a calendar when date input is clicked
//        date.setOnClickListener(view -> materialDatePicker.show(getSupportFragmentManager(), "DATE_PICKER"));
//        Set date input text when a date is selected in the calendar
//        materialDatePicker.addOnPositiveButtonClickListener(selection -> date.setText(materialDatePicker.getHeaderText()));

        Button saveBtn = findViewById(R.id.btn_save);
        saveBtn.setOnClickListener(view -> {
            insertUser();
            // End this activity
            finish();
        });
    }

    private void insertUser() {
        // Read input values
        String uId = nit.getText().toString().trim();
        String uName = name.getText().toString().trim();
        String uLastName = lastName.getText().toString().trim();
        String uPhone = phone.getText().toString().trim();
        String uEdad = age.getText().toString().trim();
        // Db Helper
        IMCDbHelper imcDbHelper = new IMCDbHelper(this);
        // Database
        SQLiteDatabase db = imcDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ImcContract.UserEntry.COLUMN_NIT, Integer.parseInt(uId));
        values.put(ImcContract.UserEntry.COLUMN_NAME, uName);
        values.put(ImcContract.UserEntry.COLUMN_LAST_NAME, uLastName);
        values.put(ImcContract.UserEntry.COLUMN_PHONE, Integer.parseInt(uPhone));
        values.put(ImcContract.UserEntry.COLUMN_AGE, Integer.parseInt(uEdad));
        if (male.isChecked()) {
            values.put(ImcContract.UserEntry.COLUMN_GENDER, ImcContract.UserEntry.GENDER_MALE);
        } else if (female.isChecked()) {
            values.put(ImcContract.UserEntry.COLUMN_GENDER, ImcContract.UserEntry.GENDER_FEMALE);
        } else {
            values.put(ImcContract.UserEntry.COLUMN_GENDER, ImcContract.UserEntry.GENDER_UNKNOWN);
        }
        Log.v("Values", values.toString());
        long newRowId = db.insert(ImcContract.UserEntry.TABLE_NAME, null, values);
        if(newRowId == -1) {
            Toast.makeText(getApplicationContext(), "Error adding the user", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "User added ID: " + newRowId, Toast.LENGTH_LONG).show();
        }
    }
}