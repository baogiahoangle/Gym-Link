package com.example.usergym;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HireFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hire, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnView1 = view.findViewById(R.id.btn_view_1);
        Button btnView2 = view.findViewById(R.id.btn_view_2);
        Button btnView3 = view.findViewById(R.id.btn_view_3);

        btnView1.setOnClickListener(v -> openDetail("Le", R.drawable.human_1, "6 năm kinh nghiệm"));
        btnView2.setOnClickListener(v -> openDetail("Pham", R.drawable.human_2, "2 năm kinh nghiệm"));
        btnView3.setOnClickListener(v -> openDetail("Dao", R.drawable.human_3, "3 năm kinh nghiệm"));
    }

    private void openDetail(String name, int avatarRes, String exp) {
        Intent intent = new Intent(getContext(), PTDetailActivity.class);
        intent.putExtra("pt_name", name);
        intent.putExtra("pt_avatar", avatarRes);
        intent.putExtra("pt_experience", exp);
        startActivity(intent);
    }
}