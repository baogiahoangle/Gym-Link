package com.example.usergym;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LogoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        Button btnConfirm = findViewById(R.id.btn_confirm_logout);
        TextView btnCancel = findViewById(R.id.btn_cancel_logout);

        btnConfirm.setOnClickListener(v -> {
            finishAffinity();
            System.exit(0);
        });

        btnCancel.setOnClickListener(v -> {
            finish();
        });
    }
}
