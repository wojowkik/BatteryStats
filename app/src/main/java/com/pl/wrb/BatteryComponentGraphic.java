package com.pl.wrb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BatteryComponentGraphic extends View
{
    Paint p;

    public BatteryComponentGraphic(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawRect(0,0,getWidth(),getHeight(), p);
    }
}
