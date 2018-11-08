package com.example.kapis.individualproject3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class LVL4Canvas extends View {

    Paint paint = new Paint();


    public LVL4Canvas(Context context){
        super(context);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(75);
    }

    public LVL4Canvas(Context context, AttributeSet attrs){
        super(context, attrs);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(75);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawLine(125, 125, 630, 125, paint);
        canvas.drawLine(590, 125, 590, 625, paint);
        canvas.drawLine(590, 590, 125, 590, paint);
    }
}
