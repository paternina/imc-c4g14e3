package com.example.imcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.imcapp.adapters.UsersListAdapter;
import com.example.imcapp.db.IMCDbHelper;
import com.example.imcapp.db.ImcContract;

import java.util.Calendar;
import java.util.Date;

public class IMCActivity extends AppCompatActivity {
    private EditText weight, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_imc);

        loadUsers();

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        
        Button calculateBtn = findViewById(R.id.calculate_btn);

        calculateBtn.setOnClickListener(view -> {
            calculateIMC();
            finish();
        });
    }
    private void loadUsers() {
        IMCDbHelper imcDbHelper = new IMCDbHelper(this);
        SQLiteDatabase db = imcDbHelper.getReadableDatabase();
        String[] userProjection = {
                ImcContract.UserEntry._ID,
                ImcContract.UserEntry.COLUMN_NIT,
                ImcContract.UserEntry.COLUMN_NAME,
                ImcContract.UserEntry.COLUMN_LAST_NAME,
                ImcContract.UserEntry.COLUMN_GENDER,
                ImcContract.UserEntry.COLUMN_AGE,
                ImcContract.UserEntry.COLUMN_PHONE
        };
        Cursor userCursor = db.query(ImcContract.UserEntry.TABLE_NAME, userProjection, null, null, null, null, null);

        UsersListAdapter userAdapter = new UsersListAdapter(this, userCursor);
        ListView usersList = findViewById(R.id.userslist);
        usersList.setAdapter(userAdapter);
    }
    public void calculateIMC() {
        String pText = weight.getText().toString().trim();
        String sText = height.getText().toString().trim();
        double p = parseDouble(pText);
        double s = parseDouble(sText);
        // IMC Result
        double res = p / (s * s);

        insertIMCData(p, s, res);
        // Clean inputs
        weight.setText("");
        height.setText("");
    }

    public void insertIMCData(Double heightValue, Double weightValue, Double result) {

        Date dateValue = Calendar.getInstance().getTime();
        // Db Helper
        IMCDbHelper imcDbHelper = new IMCDbHelper(this);
        // Database
        SQLiteDatabase db = imcDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ImcContract.IMCEntry.COLUMN_DATE, String.valueOf(dateValue));
        values.put(ImcContract.IMCEntry.COLUMN_HEIGHT, heightValue);
        values.put(ImcContract.IMCEntry.COLUMN_WEIGHT, weightValue);
        values.put(ImcContract.IMCEntry.COLUMN_RESULT, result);
        values.put(ImcContract.IMCEntry.COLUMN_USER_ID, 1);
        long newRowId = db.insert(ImcContract.IMCEntry.TABLE_NAME, null, values);
        if(newRowId == -1) {
            Toast.makeText(getApplicationContext(), "Error adding the IMC", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "IMC registered with ID: " + newRowId, Toast.LENGTH_LONG).show();
        }
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
