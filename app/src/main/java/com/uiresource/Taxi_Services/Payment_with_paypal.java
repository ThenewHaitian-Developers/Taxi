package com.uiresource.Taxi_Services;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Payment_with_paypal extends AppCompatActivity {

    TextView txtId, txtAmount, txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_with_paypal);

        txtId = (TextView)findViewById(R.id.txtId);
        txtAmount = (TextView)findViewById(R.id.edtAmount);
        txtStatus = (TextView)findViewById(R.id.txtStatus);

        Intent intent = getIntent();

        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetails"));
            showDetails(jsonObject.getJSONObject("response"), intent.getStringExtra("PaymentAmount"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void showDetails(JSONObject response, String paymentAmount) {
        try {
            txtId.setText(response.getString("id"));
            txtStatus.setText(response.getString("statu"));
            txtAmount.setText("$"+paymentAmount);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}