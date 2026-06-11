package com.example.usergym;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddScheduleActivity extends AppCompatActivity {

    private ImageButton btnClose;
    private Button btnSave;
    private TextView tvSelectedDate;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        btnClose = findViewById(R.id.btn_close);
        btnSave = findViewById(R.id.btn_save);
        tvSelectedDate = findViewById(R.id.tv_selected_date);
        timePicker = findViewById(R.id.time_picker);

        long dateMillis = getIntent().getLongExtra("selected_date", System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateMillis);

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMMM yyyy", Locale.getDefault());
        tvSelectedDate.setText(sdf.format(calendar.getTime()));

        btnClose.setOnClickListener(v -> finish());

        setupDetailItems();

        btnSave.setOnClickListener(v -> {
            String workoutName = "Upperbody Workout";
            View itemWorkout = findViewById(R.id.item_choose_workout);
            if (itemWorkout != null) {
                workoutName = ((TextView) itemWorkout.findViewById(R.id.tv_value)).getText().toString();
            }

            String duration = "20 phút";
            View itemDuration = findViewById(R.id.item_duration);
            if (itemDuration != null) {
                duration = ((TextView) itemDuration.findViewById(R.id.tv_value)).getText().toString();
            }

            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();
            String amPm = (hour >= 12) ? "PM" : "AM";
            int hourDisplay = (hour > 12) ? hour - 12 : (hour == 0 ? 12 : hour);
            String timeStr = String.format(Locale.getDefault(), "%02d:%02d %s", hourDisplay, minute, amPm);

            WorkoutTask task = new WorkoutTask(timeStr, workoutName, duration);
            
            Intent resultIntent = new Intent();
            resultIntent.putExtra("new_task", task);
            setResult(RESULT_OK, resultIntent);
            
            Toast.makeText(this, "Lịch tập đã được lưu!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void setupDetailItems() {
        View itemWorkout = findViewById(R.id.item_choose_workout);
        if (itemWorkout != null) {
            ((TextView) itemWorkout.findViewById(R.id.tv_label)).setText("Chọn bài tập");
            ((TextView) itemWorkout.findViewById(R.id.tv_value)).setText("Upperbody Workout");
            ((ImageView) itemWorkout.findViewById(R.id.iv_icon)).setImageResource(android.R.drawable.ic_menu_search);
        }

        View itemDuration = findViewById(R.id.item_duration);
        if (itemDuration != null) {
            ((TextView) itemDuration.findViewById(R.id.tv_label)).setText("Thời gian tập");
            ((TextView) itemDuration.findViewById(R.id.tv_value)).setText("20 phút");
            ((ImageView) itemDuration.findViewById(R.id.iv_icon)).setImageResource(android.R.drawable.ic_menu_recent_history);
        }
    }
}
