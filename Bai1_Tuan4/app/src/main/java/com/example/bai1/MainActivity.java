package com.example.bai1;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    private Switch backgroundSwitch;
    private int initialBackgroundRes;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        backgroundSwitch = findViewById(R.id.background_switch);
        layout = findViewById(R.id.main);

        backgroundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int[] backgrounds = {
                        R.drawable.background,
                        R.drawable.background1,
                        R.drawable.background2
                };

                // Lưu lại background ban đầu nếu chưa lưu
                if (initialBackgroundRes == 0) {
                    initialBackgroundRes = getCurrentBackgroundRes();
                }

                if (isChecked) {
                    // Chọn một ảnh khác với ảnh ban đầu
                    int newBackground;
                    do {
                        newBackground = backgrounds[(int) (Math.random() * backgrounds.length)];
                    } while (newBackground == initialBackgroundRes);

                    layout.setBackgroundResource(newBackground);
                } else {
                    // Quay lại ảnh ban đầu
                    layout.setBackgroundResource(initialBackgroundRes);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private int getCurrentBackgroundRes() {
        // Hàm này cần được cập nhật theo cách bạn đang đặt ảnh nền,
        // do Android không cung cấp trực tiếp ID của background hiện tại.
        return R.drawable.background; // Thay thế bằng cách kiểm tra thực tế nếu cần.
    }
}
