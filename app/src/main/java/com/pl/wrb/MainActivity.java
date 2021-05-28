package com.pl.wrb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    TextView textView1, textViewBatteryLevel;
    BatteryStatistics batteryStatistics;
    RefreshThread refreshThread;
    BatteryComponentGraphic batteryComponentGraphic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        textViewBatteryLevel = findViewById(R.id.textView2);
        batteryComponentGraphic = findViewById(R.id.batteryComponentGraphic);
        batteryStatistics = new BatteryStatistics(this);

        textView1.setText(batteryStatistics.getBatteryStatus());
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshThread = new RefreshThread();
        refreshThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        refreshThread.stopMe();
    }

    class RefreshThread extends Thread
    {
        boolean isRunning = true;

        void stopMe(){
        isRunning = false;
        }
        @Override
        public void run()
        {
            while (isRunning)
            {
                try {
                    sleep(1000);
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run()
                        {
                            textView1.setText(batteryStatistics.getBatteryStatus());
                            textViewBatteryLevel.setText(batteryStatistics.getBatteryLevel()+"%");
                            batteryComponentGraphic.setBatteryState(batteryStatistics.getBatteryLevel(), batteryStatistics.isCharging());
                        }
                    });
                }
                catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
