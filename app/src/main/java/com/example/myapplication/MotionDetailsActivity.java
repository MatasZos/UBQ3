package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;

import java.util.Calendar;

public class MotionDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_motion_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        long motionTime = getIntent().getLongExtra("motionTime", 0L);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(motionTime);
        String readableTime = calendar.getTime().toString();

        TextView motionDetailsText = findViewById(R.id.motionDetailsText);
        motionDetailsText.setText("Motion Detected\nTime: " + readableTime);
    }

}