package com.example.usergym;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class BmiActivity extends AppCompatActivity {

    private EditText edtWeight, edtHeight;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        edtWeight = findViewById(R.id.edt_weight);
        edtHeight = findViewById(R.id.edt_height);
        btnCalculate = findViewById(R.id.btn_calculate);

        btnCalculate.setOnClickListener(v -> {
            String weightStr = edtWeight.getText().toString();
            String heightStr = edtHeight.getText().toString();

            if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
                float weight = Float.parseFloat(weightStr);
                float height = Float.parseFloat(heightStr) / 100; // cm to m

                if (height > 0) {
                    float bmi = weight / (height * height);
                    String result = String.format(Locale.getDefault(), "%.1f", bmi);

                    Intent intent = new Intent();
                    intent.putExtra("bmi_result", result);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });
    }
}