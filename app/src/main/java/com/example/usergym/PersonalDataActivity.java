package com.example.usergym;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalDataActivity extends AppCompatActivity {

    private EditText edtGender, edtAge, edtWeight, edtHeight;
    private Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);

        edtGender = findViewById(R.id.edt_gender);
        edtAge = findViewById(R.id.edt_age);
        edtWeight = findViewById(R.id.edt_personal_weight);
        edtHeight = findViewById(R.id.edt_personal_height);
        btnFinish = findViewById(R.id.btn_next);

        edtGender.setOnClickListener(v -> {
            String[] genders = {"Nam", "Nữ"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Chọn giới tính");
            builder.setItems(genders, (dialog, which) -> {
                edtGender.setText(genders[which]);
            });
            builder.show();
        });

        SharedPreferences sharedPref = getSharedPreferences("UserGymData", Context.MODE_PRIVATE);
        edtWeight.setText(sharedPref.getString("weight", ""));
        edtHeight.setText(sharedPref.getString("height", ""));
        edtAge.setText(sharedPref.getString("age", ""));
        edtGender.setText(sharedPref.getString("gender", ""));
        
        btnFinish.setOnClickListener(v -> {
            String weight = edtWeight.getText().toString();
            String height = edtHeight.getText().toString();
            String age = edtAge.getText().toString();
            String gender = edtGender.getText().toString();
            
            if (!weight.isEmpty() && !height.isEmpty() && !age.isEmpty() && !gender.isEmpty()) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("weight", weight);
                editor.putString("height", height);
                editor.putString("age", age);
                editor.putString("gender", gender);
                editor.apply();

                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
        });
    }
}