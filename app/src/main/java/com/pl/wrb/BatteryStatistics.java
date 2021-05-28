package com.pl.wrb;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

/*a class that allows to get information about the state of the battery */
class BatteryStatistics
{
    private Context context;
    private String statusHealth, statusPlugged;

    BatteryStatistics(Context context){
        this.context = context;
    }
    private void getBatteryStatistics() // method that allows to get information about battery from device
    {
        IntentFilter ifilter= new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);

        //EXTRA HEALTH - BATTERY_HEALTH_... - battery health check
        statusHealth = "HEALTH:\t";
        int batteryHealth = batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH,-1);
        if(batteryHealth == BatteryManager.BATTERY_HEALTH_GOOD) statusHealth += "GOOD";
        else if(batteryHealth == BatteryManager.BATTERY_HEALTH_COLD) statusHealth += "COLD";
        else if(batteryHealth == BatteryManager.BATTERY_HEALTH_DEAD) statusHealth += "DEAD";
        else if(batteryHealth == BatteryManager.BATTERY_HEALTH_OVERHEAT) statusHealth += "OVERHEAT";
        else if(batteryHealth == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) statusHealth += "OVER VOLTAGE";
        else if(batteryHealth == BatteryManager.BATTERY_HEALTH_UNKNOWN) statusHealth += "UNKNOWN";
        else if(batteryHealth == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) statusHealth += "UNSPECIFIED FAILURE";
        else statusHealth = "---";
        statusHealth += "\n";

        //EXTRA PLUGGED - BATTERY_PLUGGED_... - battery charging method test
        statusPlugged = "PLUGGED:\t";
        int batteryPlugged = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        if(batteryPlugged == BatteryManager.BATTERY_PLUGGED_AC)  statusPlugged += "AC";
        else if(batteryPlugged == BatteryManager.BATTERY_PLUGGED_USB) statusPlugged += "USB";
        else if(batteryPlugged == BatteryManager.BATTERY_PLUGGED_WIRELESS) statusPlugged += "WIRELESS";
        else statusPlugged = "UNPLUGGED";
        statusPlugged += "\n";
    }
    String getBatteryStatus() // method that allows to get all information about the battery outside class
    {
        getBatteryStatistics();
        return statusHealth + statusPlugged;
    }
}
