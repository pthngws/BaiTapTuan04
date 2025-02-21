package com.example.btt4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout backgroundLayout;
    Switch switchTheme;
    ArrayList<Integer> backgroundList;
    Random random;
    int currentBackgroundIndex; // Lưu index hình nền hiện tại

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Đảm bảo layout đúng

        // Ánh xạ View
        Button btnOpenLayout1 = findViewById(R.id.btnOpenLayout1);
        Button btnOpenLayout2 = findViewById(R.id.btnOpenLayout2);
        Button btnOpenLayout3 = findViewById(R.id.btnOpenLayout3);
        Button btnOpenLayout4 = findViewById(R.id.btnOpenLayout4);
        switchTheme = findViewById(R.id.switchTheme);
        backgroundLayout = findViewById(R.id.mainLayout);

        random = new Random();

        // Khai báo danh sách hình nền
        backgroundList = new ArrayList<>();
        backgroundList.add(R.drawable.bg1);
        backgroundList.add(R.drawable.bg2);
        backgroundList.add(R.drawable.bg3);

        // Chọn hình nền ngẫu nhiên khi mở app
        currentBackgroundIndex = random.nextInt(backgroundList.size());
        backgroundLayout.setBackgroundResource(backgroundList.get(currentBackgroundIndex));

        // Xử lý khi bấm nút mở Layout1
        btnOpenLayout1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, layout1.class);
            startActivity(intent);
        });

        btnOpenLayout2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, layout2.class);
            startActivity(intent);
        });

        btnOpenLayout3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        });

        btnOpenLayout4.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            startActivity(intent);
        });

        // Xử lý khi bật/tắt Switch để đổi hình nền
        switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Khi bật switch, đổi hình nền ngẫu nhiên không trùng với hình hiện tại
                int newBackgroundIndex;
                do {
                    newBackgroundIndex = random.nextInt(backgroundList.size());
                } while (newBackgroundIndex == currentBackgroundIndex);

                currentBackgroundIndex = newBackgroundIndex;
                backgroundLayout.setBackgroundResource(backgroundList.get(currentBackgroundIndex));
            } else {
                // Khi tắt switch, đặt lại hình nền đầu tiên trong danh sách
                backgroundLayout.setBackgroundResource(backgroundList.get(0));
            }
        });
    }
}
