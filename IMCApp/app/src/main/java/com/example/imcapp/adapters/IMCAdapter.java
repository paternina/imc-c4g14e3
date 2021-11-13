package com.example.imcapp.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.imcapp.R;
import com.example.imcapp.db.ImcContract;

public class IMCAdapter extends CursorAdapter {
    public IMCAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.imc_custom_adapter, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtId = view.findViewById(R.id.txt_id);
        TextView txtDate = view.findViewById(R.id.txt_date);
        TextView txtResult = view.findViewById(R.id.txt_result);

        int _id = cursor.getInt(cursor.getColumnIndexOrThrow(ImcContract.IMCEntry._ID));
        double result = cursor.getDouble(cursor.getColumnIndexOrThrow(ImcContract.IMCEntry.COLUMN_RESULT));
        String strDate = cursor.getString(cursor.getColumnIndexOrThrow(ImcContract.IMCEntry.COLUMN_DATE));

        txtId.setText(String.valueOf(_id));
        txtDate.setText(strDate);
        txtResult.setText(String.format("%.2f", result));
    }
}
