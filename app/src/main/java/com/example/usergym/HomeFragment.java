package com.example.usergym;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private TextView tvBmiValue;
    private Button btnViewMore, btnCheckTarget, btnW1View, btnW2View, btnW3View;
    private View btnNotification;

    private final ActivityResultLauncher<Intent> bmiResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    String bmi = result.getData().getStringExtra("bmi_result");
                    if (bmi != null) {
                        tvBmiValue.setText(bmi);
                    }
                }
            }
    );

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Ánh xạ View
        tvBmiValue = view.findViewById(R.id.tv_bmi_value);
        btnViewMore = view.findViewById(R.id.btn_view_more);
        btnCheckTarget = view.findViewById(R.id.btn_check_target);
        btnW1View = view.findViewById(R.id.btn_w1_view);
        btnW2View = view.findViewById(R.id.btn_w2_view);
        btnW3View = view.findViewById(R.id.btn_w3_view);
        btnNotification = view.findViewById(R.id.btn_notification);

        updateBmiFromPrefs();

        btnNotification.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NotificationActivity.class);
            startActivity(intent);
        });

        btnViewMore.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), BmiActivity.class);
            bmiResultLauncher.launch(intent);
        });

        btnCheckTarget.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WorkoutScheduleActivity.class);
            startActivity(intent);
        });

        btnW1View.setOnClickListener(v -> startWorkoutDetail("fullbody"));
        btnW2View.setOnClickListener(v -> startWorkoutDetail("lowbody"));
        btnW3View.setOnClickListener(v -> startWorkoutDetail("ab"));

        return view;
    }

    private void startWorkoutDetail(String type) {
        Intent intent = new Intent(getActivity(), WorkoutDetailActivity.class);
        intent.putExtra("workout_type", type);
        startActivity(intent);
    }

    private void updateBmiFromPrefs() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("UserGymData", Context.MODE_PRIVATE);
        String weightStr = sharedPref.getString("weight", "");
        String heightStr = sharedPref.getString("height", "");

        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            try {
                float weight = Float.parseFloat(weightStr);
                float height = Float.parseFloat(heightStr) / 100;
                if (height > 0) {
                    float bmi = weight / (height * height);
                    tvBmiValue.setText(String.format("%.1f", bmi));
                }
            } catch (NumberFormatException ignored) {}
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateBmiFromPrefs();
    }
}