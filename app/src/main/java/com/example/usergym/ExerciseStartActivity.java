package com.example.usergym;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ExerciseStartActivity extends AppCompatActivity {

    private ImageView ivVideoThumb, ivPlayVideo;
    private TextView tvTitle, tvInfo, tvDescription;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_start);

        ivVideoThumb = findViewById(R.id.iv_exercise_video_thumb);
        ivPlayVideo = findViewById(R.id.iv_play_video);
        tvTitle = findViewById(R.id.tv_exercise_title);
        tvInfo = findViewById(R.id.tv_exercise_info);
        tvDescription = findViewById(R.id.tv_exercise_description);
        btnSave = findViewById(R.id.btn_save_workout);

        String exerciseName = getIntent().getStringExtra("exercise_name");
        if (exerciseName != null) {
            tvTitle.setText(exerciseName);
        }

        View.OnClickListener videoClickListener = v -> {
            String videoUrl = "https://www.youtube.com/watch?v=iSSAk4XCsRA";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
            startActivity(intent);
        };

        ivVideoThumb.setOnClickListener(videoClickListener);
        ivPlayVideo.setOnClickListener(videoClickListener);

        btnSave.setOnClickListener(v -> finish());
    }
}
