package com.example.imcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.imcapp.db.IMCDbHelper;
import com.example.imcapp.db.ImcContract;

public class MainActivity extends AppCompatActivity {
    private  IMCDbHelper imcDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnNewIMC = findViewById(R.id.new_imc);
        Button btnNewUser = findViewById(R.id.new_user);

        btnNewIMC.setOnClickListener(view -> newActivityIntent(IMCActivity.class));

        btnNewUser.setOnClickListener(view -> newActivityIntent(UserActivity.class));

        imcDbHelper = new IMCDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDBInformation();
    }

    public void newActivityIntent(Class<?> targetClass) {
        Intent intent = new Intent(MainActivity.this, targetClass);
        startActivity(intent);
    }

    public void displayDBInformation() {
        SQLiteDatabase db = imcDbHelper.getReadableDatabase();
        Cursor userCursor = db.rawQuery("SELECT * FROM " + ImcContract.UserEntry.TABLE_NAME, null);
        Cursor imcCursor = db.rawQuery("SELECT * FROM " + ImcContract.IMCEntry.TABLE_NAME, null);
        try {
            TextView imcInfo = findViewById(R.id.imc_information);
            imcInfo.setText("Number of users: " + userCursor.getCount() + "\n Number of IMC Records: " + imcCursor.getCount());
        } finally {
            userCursor.close();
            imcCursor.close();
        }
    }
}
