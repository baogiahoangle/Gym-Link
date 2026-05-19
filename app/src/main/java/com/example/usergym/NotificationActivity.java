package com.example.usergym;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

        RecyclerView rvNotifications = findViewById(R.id.rv_notifications);
        rvNotifications.setLayoutManager(new LinearLayoutManager(this));

        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification("Này, đến giờ ăn trưa rồi", "Khoảng 1 phút trước", R.drawable.oatmeal));
        notifications.add(new Notification("Đừng bỏ lỡ bài tập thân dưới của bạn", "Khoảng 3 giờ trước", R.drawable.workout2));
        notifications.add(new Notification("Này, hãy thêm một số bữa ăn cho b..", "Khoảng 3 giờ trước", R.drawable.drink));
        notifications.add(new Notification("Chúc mừng, Bạn đã hoàn thành A..", "29 Tháng 5", R.drawable.workout1));
        notifications.add(new Notification("Này, đến giờ ăn trưa rồi", "8 Tháng 4", R.drawable.bottle));
        notifications.add(new Notification("Rất tiếc, Bạn đã bỏ lỡ Lowerbo...", "3 Tháng 4", R.drawable.workout3));

        NotificationAdapter adapter = new NotificationAdapter(notifications);
        rvNotifications.setAdapter(adapter);
    }
}