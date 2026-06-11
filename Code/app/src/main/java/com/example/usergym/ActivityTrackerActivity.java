package com.example.usergym;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityTrackerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_tracker);

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }
}