package com.pl.wrb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    TextView textView1;
    BatteryStatistics batteryStatistics;
    RefreshThread refreshThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
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
