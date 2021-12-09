package com.example.imcapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.imcapp.adapters.IMCAdapter;
import com.example.imcapp.db.IMCDbHelper;
import com.example.imcapp.db.ImcContract;

public class MainActivity extends AppCompatActivity {
    private  IMCDbHelper imcDbHelper;
    private Button btnNewUser, btnNewIMC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewIMC = findViewById(R.id.new_imc);
        btnNewUser = findViewById(R.id.new_user);

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
        String[] userProjection = {
                ImcContract.UserEntry._ID,
                ImcContract.UserEntry.COLUMN_NIT,
                ImcContract.UserEntry.COLUMN_NAME,
                ImcContract.UserEntry.COLUMN_LAST_NAME,
                ImcContract.UserEntry.COLUMN_GENDER,
                ImcContract.UserEntry.COLUMN_AGE,
                ImcContract.UserEntry.COLUMN_PHONE
        };

        String[] imcProjection = {
                ImcContract.IMCEntry._ID,
                ImcContract.IMCEntry.COLUMN_DATE,
                ImcContract.IMCEntry.COLUMN_HEIGHT,
                ImcContract.IMCEntry.COLUMN_WEIGHT,
                ImcContract.IMCEntry.COLUMN_RESULT,
        };

        Cursor userCursor = db.query(ImcContract.UserEntry.TABLE_NAME, userProjection, null, null, null, null, null);
        Cursor imcCursor = db.query(ImcContract.IMCEntry.TABLE_NAME, imcProjection, null, null, null, null, null);

        IMCAdapter imcAdapter = new IMCAdapter(this, imcCursor);
        ListView rView = findViewById(R.id.imc_list);
        rView.setAdapter(imcAdapter);
    }
}
