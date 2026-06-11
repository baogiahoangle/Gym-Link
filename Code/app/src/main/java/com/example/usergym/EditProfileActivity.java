package com.example.usergym;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private EditText edtName, edtProgram;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        edtName = findViewById(R.id.edt_name);
        edtProgram = findViewById(R.id.edt_program);
        btnSave = findViewById(R.id.btn_save);

        SharedPreferences sharedPref = getSharedPreferences("UserGymData", Context.MODE_PRIVATE);
        String name = sharedPref.getString("profile_name", "Ja Wang");
        String program = sharedPref.getString("program", "Chương trình giảm mỡ");

        edtName.setText(name);
        edtProgram.setText(program);

        btnSave.setOnClickListener(v -> {
            String newName = edtName.getText().toString();
            String newProgram = edtProgram.getText().toString();

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("profile_name", newName);
            editor.putString("program", newProgram);
            editor.apply();

            setResult(RESULT_OK);
            finish();
        });
    }
}