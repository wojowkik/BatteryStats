package com.pl.wrb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    TextView textView1;
    BatteryStatistics batteryStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        batteryStatistics = new BatteryStatistics(this);

        textView1.setText(batteryStatistics.getBatteryStatus());
    }
}
