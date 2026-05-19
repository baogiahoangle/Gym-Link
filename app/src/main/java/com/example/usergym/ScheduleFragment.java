package com.example.usergym;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ScheduleFragment extends Fragment {

    private RecyclerView rvSchedule;
    private RecyclerView rvCalendar;
    private TextView tvMonthYear;
    private FloatingActionButton fabAdd;

    private final List<WorkoutTask> workoutTasks = new ArrayList<>();
    private ScheduleAdapter scheduleAdapter;
    private Calendar selectedDate = Calendar.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Tab views
        TextView tvTabStats = view.findViewById(R.id.tv_tab_stats);
        TextView tvTabSchedule = view.findViewById(R.id.tv_tab_schedule);
        View viewStats = view.findViewById(R.id.view_statistics);
        View viewSchedule = view.findViewById(R.id.view_schedule);

        // Schedule views
        rvSchedule = view.findViewById(R.id.rv_schedule);
        rvCalendar = view.findViewById(R.id.rv_calendar);
        tvMonthYear = view.findViewById(R.id.tv_month_year);
        fabAdd = view.findViewById(R.id.fab_add_schedule);

        // Tab Switching Logic
        tvTabStats.setOnClickListener(v -> {
            tvTabStats.setBackgroundResource(R.drawable.bg_tab_selected);
            tvTabStats.setTextColor(getResources().getColor(android.R.color.white));
            tvTabSchedule.setBackground(null);
            tvTabSchedule.setTextColor(getResources().getColor(R.color.gray_color));
            viewStats.setVisibility(View.VISIBLE);
            viewSchedule.setVisibility(View.GONE);
        });

        tvTabSchedule.setOnClickListener(v -> {
            tvTabSchedule.setBackgroundResource(R.drawable.bg_tab_selected);
            tvTabSchedule.setTextColor(getResources().getColor(android.R.color.white));
            tvTabStats.setBackground(null);
            tvTabStats.setTextColor(getResources().getColor(R.color.gray_color));
            viewStats.setVisibility(View.GONE);
            viewSchedule.setVisibility(View.VISIBLE);
        });

        setupCalendar();
        setupRecyclerView();
        updateStatisticsDays(view);

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddScheduleActivity.class);
            intent.putExtra("selected_date", selectedDate.getTimeInMillis());
            startActivityForResult(intent, 100);
        });
    }

    private void setupCalendar() {
        List<Calendar> days = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        updateMonthYearText(calendar);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int month = calendar.get(Calendar.MONTH);
        while (calendar.get(Calendar.MONTH) == month) {
            days.add((Calendar) calendar.clone());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, date -> {
            selectedDate = date;
            updateMonthYearText(date);
            Toast.makeText(getContext(), "Ngày đã chọn: " + new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date.getTime()), Toast.LENGTH_SHORT).show();
        });

        rvCalendar.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvCalendar.setAdapter(calendarAdapter);
    }

    private void updateMonthYearText(Calendar date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        tvMonthYear.setText(sdf.format(date.getTime()));
    }

    private void setupRecyclerView() {
        List<String> timeSlots = new ArrayList<>();
        for (int i = 6; i <= 11; i++) timeSlots.add(String.format(Locale.getDefault(), "%02d:00 AM", i));
        timeSlots.add("12:00 PM");
        for (int i = 1; i <= 8; i++) timeSlots.add(String.format(Locale.getDefault(), "%02d:00 PM", i));

        if (workoutTasks.isEmpty()) {
            workoutTasks.add(new WorkoutTask("07:00 AM", "Yoga buổi sáng", "30 phút"));
            workoutTasks.add(new WorkoutTask("05:00 PM", "Tập Gym tăng cơ", "60 phút"));
        }

        scheduleAdapter = new ScheduleAdapter(timeSlots, workoutTasks, this::showTaskDetailBottomSheet);
        rvSchedule.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSchedule.setAdapter(scheduleAdapter);
    }

    private void showTaskDetailBottomSheet(WorkoutTask task) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_workout_details_sheet, null);
        
        TextView tvName = view.findViewById(R.id.tv_workout_name);
        TextView tvTime = view.findViewById(R.id.tv_workout_time);
        
        tvName.setText(task.getName());
        tvTime.setText("Thời gian | " + task.getTime());

        Button btnMarkAsDone = view.findViewById(R.id.btn_mark_done);
        btnMarkAsDone.setText("Đã hoàn thành");
        btnMarkAsDone.setOnClickListener(v -> bottomSheetDialog.dismiss());

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
    }

    private void updateStatisticsDays(View view) {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // Sunday = 1, Monday = 2, ...

        // IDs for day name TextViews
        int[] dayTextViewIds = {
                R.id.tv_day_sun, R.id.tv_day_mon, R.id.tv_day_tue,
                R.id.tv_day_wed, R.id.tv_day_thu, R.id.tv_day_fri, R.id.tv_day_sat
        };

        // IDs for date TextViews
        int[] dateTextViewIds = {
                R.id.tv_date_sun, R.id.tv_date_mon, R.id.tv_date_tue,
                R.id.tv_date_wed, R.id.tv_date_thu, R.id.tv_date_fri, R.id.tv_date_sat
        };

        // Calculate dates for the current week starting from Sunday
        Calendar weekCalendar = (Calendar) calendar.clone();
        weekCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        for (int i = 0; i < 7; i++) {
            TextView tvDay = view.findViewById(dayTextViewIds[i]);
            TextView tvDate = view.findViewById(dateTextViewIds[i]);

            if (tvDay != null && tvDate != null) {
                tvDate.setText(String.valueOf(weekCalendar.get(Calendar.DAY_OF_MONTH)));

                // Highlight if it's today
                if (i + 1 == dayOfWeek) {
                    int highlightColor = getResources().getColor(R.color.blue_color); // Or #92A3FD
                    tvDay.setTextColor(highlightColor);
                    tvDay.setTypeface(null, android.graphics.Typeface.BOLD);
                    tvDate.setTextColor(highlightColor);
                    tvDate.setTypeface(null, android.graphics.Typeface.BOLD);
                } else {
                    int grayColor = getResources().getColor(R.color.gray_color);
                    tvDay.setTextColor(grayColor);
                    tvDay.setTypeface(null, android.graphics.Typeface.NORMAL);
                    tvDate.setTextColor(grayColor);
                    tvDate.setTypeface(null, android.graphics.Typeface.NORMAL);
                }
            }
            weekCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null) {
            WorkoutTask newTask = (WorkoutTask) data.getSerializableExtra("new_task");
            if (newTask != null) {
                workoutTasks.add(newTask);
                scheduleAdapter.notifyDataSetChanged();
            }
        }
    }
}
