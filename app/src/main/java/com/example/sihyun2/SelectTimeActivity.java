package com.example.sihyun2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SelectTimeActivity extends AppCompatActivity {
    private static ArrayList<String> accumulatedSelections = new ArrayList<>();
    private TimePicker timePicker;
    private Button btnDone;
    private TextView tvSelectedDate;
    private TextView tvSelectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        timePicker = findViewById(R.id.timePicker);
        btnDone = findViewById(R.id.btnDone);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);

        String selectedDate = getIntent().getStringExtra("selected_date");
        tvSelectedDate.setText("Selected Date: " + selectedDate);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hourOfDay = timePicker.getHour();
                int minute = timePicker.getMinute();
                String selectedTime = String.format("%02d:%02d", hourOfDay, minute);

                // 이전 액티비티로부터 선택한 날짜를 받아옵니다.
                String selectedDate = getIntent().getStringExtra("selected_date");

                // 선택한 날짜와 시간을 하나의 문자열로 합칩니다.
                String selectedDateTime = selectedDate + " " + selectedTime;

                // 선택한 날짜와 시간을 ArrayList에 추가합니다.
                accumulatedSelections.add(selectedDateTime);

                // 누적된 선택 내용을 화면에 표시합니다.
                StringBuilder stringBuilder = new StringBuilder();
                for (String dateTime : accumulatedSelections) {
                    stringBuilder.append(dateTime).append("\n");
                }
                tvSelectedTime.setText("누적된 선택 내용:\n" + stringBuilder.toString());

                showToast("선택한 시간: " + selectedTime);
            }
        });

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}