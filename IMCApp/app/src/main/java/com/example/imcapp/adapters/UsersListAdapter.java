package com.example.imcapp.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cursoradapter.widget.CursorAdapter;

import com.example.imcapp.R;
import com.example.imcapp.db.ImcContract;

public class UsersListAdapter extends CursorAdapter {
    public UsersListAdapter(Context context, Cursor c) {
        super(context, c);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.userslist_custom_adapter, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtId = view.findViewById(R.id.user_nit);
        TextView txtName = view.findViewById(R.id.user_name);
        LinearLayout user = view.findViewById(R.id.user);

        int _id = cursor.getInt(cursor.getColumnIndexOrThrow(ImcContract.UserEntry._ID));
        String nit = cursor.getString(cursor.getColumnIndexOrThrow(ImcContract.UserEntry.COLUMN_NIT));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(ImcContract.UserEntry.COLUMN_NAME))
                + " "
                + cursor.getString(cursor.getColumnIndexOrThrow(ImcContract.UserEntry.COLUMN_LAST_NAME));

        txtId.setText(nit);
        txtName.setText(name);
        user.setOnClickListener(view1 -> {
            ContentValues values = new ContentValues();
            values.put("id", _id );
            Toast.makeText(context, "You have selected: " + name, Toast.LENGTH_LONG).show();
        });
    }
}
