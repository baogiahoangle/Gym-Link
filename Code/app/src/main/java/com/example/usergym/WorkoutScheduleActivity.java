package com.example.usergym;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class WorkoutScheduleActivity extends AppCompatActivity {

    private RecyclerView rvSchedule;
    private RecyclerView rvCalendar;
    private TextView tvMonthYear;
    private FloatingActionButton fabAdd;
    private ImageButton btnBack;

    private final List<WorkoutTask> workoutTasks = new ArrayList<>();
    private ScheduleAdapter scheduleAdapter;
    private Calendar selectedDate = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_schedule);

        rvSchedule = findViewById(R.id.rv_schedule);
        rvCalendar = findViewById(R.id.rv_calendar);
        tvMonthYear = findViewById(R.id.tv_month_year);
        fabAdd = findViewById(R.id.fab_add_schedule);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(v -> finish());

        // Setup Calendar
        setupCalendar();

        // Setup RecyclerView with dummy data for hours 06:00 AM to 08:00 PM
        setupRecyclerView();

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddScheduleActivity.class);
            intent.putExtra("selected_date", selectedDate.getTimeInMillis());
            startActivityForResult(intent, 100);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            WorkoutTask newTask = (WorkoutTask) data.getSerializableExtra("new_task");
            if (newTask != null) {
                workoutTasks.add(newTask);
                scheduleAdapter.notifyDataSetChanged();
            }
        }
    }

    private void setupCalendar() {
        List<Calendar> days = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        
        // Get current month and year for display
        updateMonthYearText(calendar);

        // Generate days for the current month
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int month = calendar.get(Calendar.MONTH);
        while (calendar.get(Calendar.MONTH) == month) {
            days.add((Calendar) calendar.clone());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, date -> {
            selectedDate = date;
            updateMonthYearText(date);
            // Here you could also filter the schedule based on the selected date
            Toast.makeText(this, "Selected: " + new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date.getTime()), Toast.LENGTH_SHORT).show();
        });

        rvCalendar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCalendar.setAdapter(calendarAdapter);

        // Scroll to current day
        Calendar today = Calendar.getInstance();
        for (int i = 0; i < days.size(); i++) {
            if (days.get(i).get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)) {
                rvCalendar.scrollToPosition(i);
                break;
            }
        }
    }

    private void updateMonthYearText(Calendar date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        tvMonthYear.setText(sdf.format(date.getTime()));
    }

    private void setupRecyclerView() {
        List<String> timeSlots = new ArrayList<>();
        for (int i = 6; i <= 11; i++) {
            timeSlots.add(String.format(Locale.getDefault(), "%02d:00 AM", i));
        }
        timeSlots.add("12:00 PM");
        for (int i = 1; i <= 8; i++) {
            timeSlots.add(String.format(Locale.getDefault(), "%02d:00 PM", i));
        }

        // Add a default task for demo
        workoutTasks.add(new WorkoutTask("03:00 PM", "Lowerbody Workout", "20 phút"));

        scheduleAdapter = new ScheduleAdapter(timeSlots, workoutTasks, this::showTaskDetailBottomSheet);
        rvSchedule.setLayoutManager(new LinearLayoutManager(this));
        rvSchedule.setAdapter(scheduleAdapter);
    }

    private void showTaskDetailBottomSheet(WorkoutTask task) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_workout_details_sheet, null);
        
        TextView tvName = view.findViewById(R.id.tv_workout_name);
        TextView tvTime = view.findViewById(R.id.tv_workout_time);
        
        tvName.setText(task.getName());
        tvTime.setText("Hôm nay | " + task.getTime());

        Button btnMarkAsDone = view.findViewById(R.id.btn_mark_done);
        btnMarkAsDone.setOnClickListener(v -> bottomSheetDialog.dismiss());

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }
}