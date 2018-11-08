package com.example.kapis.individualproject3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class LVL1Canvas extends View {

    Paint paint = new Paint();
    Bitmap bitmap;


    public LVL1Canvas(Context context){
        super(context);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(75);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.win);
    }

    public LVL1Canvas(Context context, AttributeSet attrs){
        super(context, attrs);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(75);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.win);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawLine(100, 100, 613, 100, paint);
        canvas.drawLine(575, 75, 575, 600, paint);
    }
}
