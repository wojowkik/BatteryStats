package com.pl.wrb;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/*a class that allows to get information about the state of the battery */
class BatteryStatistics
{
    private Context context;
    private String status = "";

    BatteryStatistics(Context context){
        this.context = context;
    }
    private void getBatteryStatistics() // method that allows to get information about battery from device
    {
        IntentFilter ifilter= new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
    }
    String getBatteryStatus() // method that allows to get all information about the battery outside class
    {
        getBatteryStatistics();
        return status;
    }
}
