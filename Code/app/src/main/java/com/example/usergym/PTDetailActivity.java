package com.example.usergym;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PTDetailActivity extends AppCompatActivity {

    private ImageView ivAvatar;
    private TextView tvName, tvBio, tvDescription, tvStatTotal, tvStatToday, tvStatWeek, tvStatMonth;
    private LinearLayout llFeedbackContainer;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pt_detail);

        ivAvatar = findViewById(R.id.iv_detail_pt_avatar);
        tvName = findViewById(R.id.tv_detail_pt_name);
        tvBio = findViewById(R.id.tv_detail_pt_bio);
        tvDescription = findViewById(R.id.tv_pt_description);
        tvStatTotal = findViewById(R.id.tv_stat_total);
        tvStatToday = findViewById(R.id.tv_stat_today);
        tvStatWeek = findViewById(R.id.tv_stat_week);
        tvStatMonth = findViewById(R.id.tv_stat_month);
        llFeedbackContainer = findViewById(R.id.ll_feedback_container);
        btnBack = findViewById(R.id.btn_back_pt);

        btnBack.setOnClickListener(v -> finish());

        String name = getIntent().getStringExtra("pt_name");
        int avatarRes = getIntent().getIntExtra("pt_avatar", R.drawable.human_1);

        tvName.setText(name);
        ivAvatar.setImageResource(avatarRes);

        setupPTData(name);
    }

    private void setupPTData(String name) {
        llFeedbackContainer.removeAllViews();

        if ("Le".equals(name)) {
            tvBio.setText("Huấn luyện viên Cao cấp @GymMaster");
            tvDescription.setText("Chuyên gia với 6 năm kinh nghiệm trong việc xây dựng cơ bắp và cải thiện vóc dáng. Đã giúp hơn 500 học viên đạt được mục tiêu hình thể mong muốn thông qua các bài tập kháng lực và chế độ dinh dưỡng nghiêm ngặt.");
            tvStatTotal.setText("1200+");
            tvStatToday.setText("5");
            tvStatWeek.setText("28");
            tvStatMonth.setText("95");

            addFeedback("Nguyễn Văn An", "⭐⭐⭐⭐⭐", "HLV rất có tâm, hướng dẫn kỹ thuật Squat cực chuẩn giúp mình không còn đau lưng.", R.drawable.trainee_1);
            addFeedback("Trần Thị Bình", "⭐⭐⭐⭐", "Lộ trình khá nặng nhưng hiệu quả rõ rệt. Giảm 5kg sau 2 tháng.", R.drawable.trainee_2);
            addFeedback("Lê Hoàng Nam", "⭐⭐⭐⭐⭐", "Kiến thức dinh dưỡng của anh Lê rất sâu, giúp mình hiểu rõ về Macro.", R.drawable.trainee_3);

        } else if ("Pham".equals(name)) {
            tvBio.setText("Chuyên gia Yoga & Pilates @ZenFlow");
            tvDescription.setText("Với 2 năm kinh nghiệm, tôi tập trung vào sự dẻo dai và cân bằng tâm trí. Các bài tập của tôi giúp giảm căng thẳng, cải thiện tư thế và tăng cường sức mạnh cốt lõi một cách nhẹ nhàng nhưng hiệu quả.");
            tvStatTotal.setText("450+");
            tvStatToday.setText("2");
            tvStatWeek.setText("10");
            tvStatMonth.setText("38");

            addFeedback("Hoàng Mỹ Linh", "⭐⭐⭐⭐⭐", "Cô Phạm dạy rất nhẹ nhàng, sau mỗi buổi tập mình cảm thấy tinh thần cực kỳ sảng khoái.", R.drawable.trainee_4);
            addFeedback("Phạm Minh Tuấn", "⭐⭐⭐", "Bài tập hơi nhẹ so với mong muốn của mình, nhưng rất tốt cho việc phục hồi cơ bắp.", R.drawable.trainee_5);
            addFeedback("Đặng Thu Thảo", "⭐⭐⭐⭐⭐", "Tư thế Yoga của mình đã cải thiện rõ rệt sau khi theo học cô Phạm.", R.drawable.trainee_6);

        } else if ("Dao".equals(name)) {
            tvBio.setText("HLV Giảm cân & Cardio @SpeedFit");
            tvDescription.setText("Sở trường là các bài tập cường độ cao (HIIT) và Cardio. Tôi sẽ giúp bạn đốt cháy mỡ thừa hiệu quả nhất trong thời gian ngắn nhất với lộ trình 3 năm kinh nghiệm thực chiến.");
            tvStatTotal.setText("800+");
            tvStatToday.setText("4");
            tvStatWeek.setText("20");
            tvStatMonth.setText("60");

            addFeedback("Bùi Gia Bảo", "⭐⭐⭐⭐⭐", "Cardio của anh Đào thực sự là 'cực hình' nhưng kết quả thì không chê vào đâu được!", R.drawable.trainee_7);
            addFeedback("Vũ Phương Anh", "⭐⭐⭐⭐", "Nhiệt tình, luôn thúc đẩy học viên vượt qua giới hạn của bản thân.", R.drawable.trainee_1);
            addFeedback("Ngô Quốc Việt", "⭐⭐⭐⭐⭐", "Đã giảm mỡ bụng thành công nhờ bài tập HIIT của HLV Đào.", R.drawable.trainee_2);
        }
    }

    private void addFeedback(String name, String stars, String comment, int imageRes) {
        View feedbackView = LayoutInflater.from(this).inflate(R.layout.item_feedback, llFeedbackContainer, false);
        
        ImageView ivTrainee = feedbackView.findViewById(R.id.iv_trainee_avatar);
        TextView tvTraineeName = feedbackView.findViewById(R.id.tv_trainee_name);
        TextView tvStars = feedbackView.findViewById(R.id.tv_stars);
        TextView tvComment = feedbackView.findViewById(R.id.tv_comment);

        ivTrainee.setImageResource(imageRes);
        tvTraineeName.setText(name);
        tvStars.setText(stars);
        tvComment.setText(comment);

        llFeedbackContainer.addView(feedbackView);
    }
}
