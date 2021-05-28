package com.pl.wrb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BatteryComponentGraphic extends View
{
    Paint p;
    float batteryLevel = 30;
    boolean isCharging = false;
    int batteryColor = Color.GREEN, paddingBattery = 10;

    public BatteryComponentGraphic(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        paddingBattery = getWidth()/30;
        canvas.drawRect(0,getHeight()/15,getWidth(),getHeight(), p);//background/border
        canvas.drawRect((getWidth()/5)*2,0,(getWidth()/5)*3,getHeight()/15, p);//plus
        p.setColor(batteryColor);
        canvas.drawRect(paddingBattery,getHeight()-(getHeight()-getHeight()/15-paddingBattery)*batteryLevel/100,getWidth()-paddingBattery,getHeight()-paddingBattery, p);//main
        p.setColor(Color.BLACK);
    }
    void setBatteryState(float batteryLevel, boolean isCharging)
    {
        if(batteryLevel >= 0 && batteryLevel <= 100) this.batteryLevel = batteryLevel;
        this.isCharging = isCharging;
        setColorPreferences();

        invalidate();
    }
    private void setColorPreferences()
    {
        if(batteryLevel<20) {
            batteryColor = Color.RED;
        }
        else if(batteryLevel<50) {
            batteryColor = Color.YELLOW;
        }
        else if(isCharging) {
            batteryColor = Color.MAGENTA;
        }
        else {
            batteryColor = Color.GREEN;
        }
    }
}
