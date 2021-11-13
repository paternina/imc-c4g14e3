package com.example.imcapp.viewholders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imcapp.R;

public class IMCViewHolder extends RecyclerView.ViewHolder {
    private TextView txtDate, txtId, txtResult;
    private ImageButton btnImcDetail;

    public IMCViewHolder(@NonNull View itemView) {
        super(itemView);
        txtId = (TextView) itemView.findViewById(R.id.txt_id);
        txtDate = (TextView) itemView.findViewById(R.id.txt_date);
        txtResult = (TextView) itemView.findViewById(R.id.txt_result);
        btnImcDetail = (ImageButton) itemView.findViewById(R.id.btn_imc_detail);
    }

    public TextView getTxtDate() {
        return txtDate;
    }

    public TextView getTxtId() {
        return txtId;
    }

    public TextView getTxtResult() {
        return txtResult;
    }

    public ImageButton getBtnImcDetail() {
        return btnImcDetail;
    }
}
