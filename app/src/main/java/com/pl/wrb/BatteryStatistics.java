package com.pl.wrb;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

/*a class that allows to get information about the state of the battery */
// https://developer.android.com/training/monitoring-device-state/battery-monitoring
class BatteryStatistics
{
    private Context context;
    private String statusHealth, statusPlugged, statusProperty, statusCharging, statusLevel,
                    statusTechnology, statusTemperature, statusVoltage;
    private float batteryLevel;

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

        //BATTERY_PROPERTY_... - battery properties test
        statusProperty = "CAPACITY:\t" + BatteryManager.BATTERY_PROPERTY_CAPACITY + "%\n";
        statusProperty += "CHARGE COUNTER:\t" + BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER + "uAh\n";
        statusProperty += "CURRENT AVERAGE:\t" + BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE + "uA\n";
        statusProperty += "CURRENT NOW:\t" + BatteryManager.BATTERY_PROPERTY_CURRENT_NOW + "uA\n";
        statusProperty += "ENERGY COUNTER:\t" + BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER + "nWh\n";
        statusProperty += "PROPERTY STATUS:\t" + BatteryManager.BATTERY_PROPERTY_STATUS + "\n";

        //EXTRA STATUS - BATTERY_STATUS_... - battery status check
        statusCharging = "STATUS:\t";
        int batteryCharging = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        if(batteryCharging == BatteryManager.BATTERY_STATUS_CHARGING) statusCharging += "CHARGING";
        else if(batteryCharging == BatteryManager.BATTERY_STATUS_DISCHARGING) statusCharging += "DISCHARGING";
        else if(batteryCharging == BatteryManager.BATTERY_STATUS_FULL) statusCharging += "FULL";
        else if(batteryCharging == BatteryManager.BATTERY_STATUS_NOT_CHARGING) statusCharging += "NOT CHARGING";
        else if(batteryCharging == BatteryManager.BATTERY_STATUS_UNKNOWN) statusCharging += "UNKNOWN";
        else statusCharging += "---";
        statusCharging += "\n";

        //EXTRA_LEVEL
        // Extra for Intent.ACTION_BATTERY_CHANGED: integer field containing the current battery level, from 0 to EXTRA_SCALE.
        statusLevel = "LEVEL\t";
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        batteryLevel = ((float) level / (float) scale) *100;
        statusLevel += batteryLevel+ "%\n" ;

        //EXTRA_TECHNOLOGY
        // Extra for Intent.ACTION_BATTERY_CHANGED: String describing the technology of the current battery.
        statusTechnology = "BATTERY TECHNOLOGY:\t" + batteryStatus.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY) + "\n";

        //EXTRA_TEMPERATURE
        //Extra for Intent.ACTION_BATTERY_CHANGED: integer containing the current battery temperature.
        int temperature = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) / 10;
        statusTemperature = "TEMPERATURE: " + temperature + "°C\n";

        //EXTRA_VOLTAGE
        //Extra for Intent.ACTION_BATTERY_CHANGED: integer containing the current battery voltage level.
        float voltage = (float) batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1) / 1000;
        statusVoltage = "VOLTAGE: " + voltage + " V\n";
    }
    String getBatteryStatus() // method that allows to get all information about the battery outside class
    {
        getBatteryStatistics();
        return statusLevel + statusHealth + statusCharging + statusPlugged  + statusTemperature + statusVoltage +
                "\nADDITIONAL PROPERTIES\n"+ statusTechnology  + statusProperty;
    }
}
