package com.example.usergym;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class WorkoutDetailActivity extends AppCompatActivity {

    private RecyclerView rvEquipment, rvExercises;
    private TextView tvWorkoutName, tvWorkoutInfo;
    private ImageView ivWorkoutMain;
    private Button btnStartWorkout;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        rvEquipment = findViewById(R.id.rv_equipment);
        rvExercises = findViewById(R.id.rv_exercises);
        tvWorkoutName = findViewById(R.id.tv_workout_name);
        tvWorkoutInfo = findViewById(R.id.tv_workout_info);
        ivWorkoutMain = findViewById(R.id.iv_workout_main);
        btnStartWorkout = findViewById(R.id.btn_start_workout);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(v -> finish());

        String workoutType = getIntent().getStringExtra("workout_type");
        setupData(workoutType);

        btnStartWorkout.setOnClickListener(v -> {
            Intent intent = new Intent(this, ExerciseStartActivity.class);
            startActivity(intent);
        });
    }

    private void setupData(String type) {
        List<Equipment> equipments = new ArrayList<>();
        equipments.add(new Equipment("Tạ tay", R.drawable.barbell));
        equipments.add(new Equipment("Dây nhảy", R.drawable.skipping_rope));
        equipments.add(new Equipment("Bình nước", R.drawable.bottle));
        equipments.add(new Equipment("Giày", R.drawable.foot));
        equipments.add(new Equipment("Yến mạch", R.drawable.oatmeal));

        rvEquipment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvEquipment.setAdapter(new EquipmentAdapter(equipments));

        List<Exercise> exercises = new ArrayList<>();
        if ("fullbody".equals(type)) {
            tvWorkoutName.setText("Fullbody Workout");
            tvWorkoutInfo.setText("11 Exercises | 32 mins | 320 Calories");
            ivWorkoutMain.setImageResource(R.drawable.goal_2);

            exercises.add(new Exercise("Warm Up", "05:00", R.drawable.warmup));
            exercises.add(new Exercise("Jumping Jacks", "12 times", R.drawable.jumpingjack));
            exercises.add(new Exercise("Skipping", "15 times", R.drawable.skipping));
            exercises.add(new Exercise("Squats", "20 times", R.drawable.squats));
            exercises.add(new Exercise("Arm Raises", "00:53", R.drawable.armraise));
            exercises.add(new Exercise("Push Ups", "12 times", R.drawable.pushup));
            exercises.add(new Exercise("Plank", "01:00", R.drawable.plank));
            exercises.add(new Exercise("Lunges", "15 times", R.drawable.lunge));
            exercises.add(new Exercise("Burpees", "10 times", R.drawable.burpee));
            exercises.add(new Exercise("Rest", "02:00", R.drawable.drink));
            exercises.add(new Exercise("Cool Down", "05:00", R.drawable.warmup));
        } else if ("lowbody".equals(type)) {
            tvWorkoutName.setText("Lowbody Workout");
            tvWorkoutInfo.setText("12 Exercises | 40 mins | 400 Calories");
            ivWorkoutMain.setImageResource(R.drawable.goal_3);

            exercises.add(new Exercise("Warm Up", "05:00", R.drawable.warmup));
            exercises.add(new Exercise("Squats", "25 times", R.drawable.squats));
            exercises.add(new Exercise("Lunges", "20 times", R.drawable.lunge));
            exercises.add(new Exercise("Calf Raises", "20 times", R.drawable.calfrise));
            exercises.add(new Exercise("Leg Press", "15 times", R.drawable.legpress));
            exercises.add(new Exercise("Glute Bridge", "20 times", R.drawable.glutebridge));
            exercises.add(new Exercise("Wall Sit", "01:00", R.drawable.warmup));
            exercises.add(new Exercise("Side Lunges", "15 times", R.drawable.lunge));
            exercises.add(new Exercise("Rest", "02:00", R.drawable.drink));
            exercises.add(new Exercise("Step Ups", "15 times", R.drawable.warmup));
            exercises.add(new Exercise("Leg Curls", "12 times", R.drawable.warmup));
            exercises.add(new Exercise("Cool Down", "05:00", R.drawable.warmup));
        } else if ("ab".equals(type)) {
            tvWorkoutName.setText("AB Workout");
            tvWorkoutInfo.setText("14 Exercises | 20 mins | 250 Calories");
            ivWorkoutMain.setImageResource(R.drawable.goal_4);

            exercises.add(new Exercise("Warm Up", "03:00", R.drawable.warmup));
            exercises.add(new Exercise("Crunches", "20 times", R.drawable.crunch));
            exercises.add(new Exercise("Plank", "01:30", R.drawable.plank));
            exercises.add(new Exercise("Leg Raises", "15 times", R.drawable.legraise));
            exercises.add(new Exercise("Bicycle Crunches", "20 times", R.drawable.crunch));
            exercises.add(new Exercise("Mountain Climbers", "30 sec", R.drawable.warmup));
            exercises.add(new Exercise("Heel Touches", "20 times", R.drawable.warmup));
            exercises.add(new Exercise("Rest", "01:30", R.drawable.drink));
            exercises.add(new Exercise("Sit Ups", "20 times", R.drawable.warmup));
            exercises.add(new Exercise("Russian Twist", "20 times", R.drawable.warmup));
            exercises.add(new Exercise("V-Ups", "12 times", R.drawable.warmup));
            exercises.add(new Exercise("Bird Dog", "15 times", R.drawable.warmup));
            exercises.add(new Exercise("Side Plank", "01:00", R.drawable.plank));
            exercises.add(new Exercise("Cool Down", "04:00", R.drawable.warmup));
        } else {
            tvWorkoutName.setText("Workout Detail");
            tvWorkoutInfo.setText("0 Bài tập | 0 phút | 0 Calo tiêu thụ");
            ivWorkoutMain.setImageResource(R.drawable.goal_2);
        }

        rvExercises.setLayoutManager(new LinearLayoutManager(this));
        rvExercises.setAdapter(new ExerciseAdapter(exercises, exercise -> {
            Intent intent = new Intent(this, ExerciseStartActivity.class);
            intent.putExtra("exercise_name", exercise.getName());
            startActivity(intent);
        }));
    }
}
