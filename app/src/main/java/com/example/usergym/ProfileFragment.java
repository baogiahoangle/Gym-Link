package com.example.usergym;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private TextView tvHeight, tvWeight, tvAge, tvProfileName, tvProgram;
    private View btnPersonalData, btnActivityTracker, btnEditProfile;

    private final ActivityResultLauncher<Intent> editProfileLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    loadUserData();
                }
            }
    );

    private final ActivityResultLauncher<Intent> personalDataLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    loadUserData();
                }
            }
    );

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvHeight = view.findViewById(R.id.tv_height);
        tvWeight = view.findViewById(R.id.tv_weight);
        tvAge = view.findViewById(R.id.tv_age);
        tvProfileName = view.findViewById(R.id.tv_profile_name);
        tvProgram = view.findViewById(R.id.tv_program);
        btnPersonalData = view.findViewById(R.id.btn_personal_data);
        btnActivityTracker = view.findViewById(R.id.btn_activity_tracker);
        btnEditProfile = view.findViewById(R.id.btn_edit_profile);

        loadUserData();

        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditProfileActivity.class);
            editProfileLauncher.launch(intent);
        });

        btnPersonalData.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PersonalDataActivity.class);
            personalDataLauncher.launch(intent);
        });

        btnActivityTracker.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ActivityTrackerActivity.class);
            startActivity(intent);
        });

        return view;
    }

    private void loadUserData() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("UserGymData", Context.MODE_PRIVATE);
        String height = sharedPref.getString("height", "--");
        String weight = sharedPref.getString("weight", "--");
        String age = sharedPref.getString("age", "--");
        String name = sharedPref.getString("profile_name", "Ja Wang");
        String program = sharedPref.getString("program", "Chương trình giảm mỡ");

        tvHeight.setText(height.equals("--") ? "--" : height + "cm");
        tvWeight.setText(weight.equals("--") ? "--" : weight + "kg");
        tvAge.setText(age.equals("--") ? "--" : age + "yo");
        tvProfileName.setText(name);
        tvProgram.setText(program);
    }
}