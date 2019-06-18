package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.view.MotionEventCompat;

public class JoyStickView extends View {
    private Paint colors =new Paint();
    Canvas canvas;
    private Paint freeCellPaint;
    private Paint wallPaint;

    public JoyStickView(Context context) {
        super(context);
        freeCellPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        freeCellPaint.setColor(Color.WHITE);
        freeCellPaint.setStyle(Paint.Style.FILL);
        wallPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        wallPaint.setColor(Color.BLACK);
        wallPaint.setStyle(Paint.Style.FILL);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onDraw(Canvas canvas) {
        this.canvas=canvas;
        super.onDraw(canvas);
        colors.setColor(Color.GRAY);
        canvas.drawOval(getWidth()/2 -400,getHeight()/2 + 500, getWidth()/2 + 400,  getHeight()/2 - 500, colors );
        colors.setColor(Color.BLACK);
        canvas.drawCircle(getWidth()/2,getHeight()/2,130, colors);

    }
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                float x = event.getX();
                float y = event.getY();
                canvas.drawCircle(x,y,200,colors);
                break;
            }
            case MotionEvent.ACTION_MOVE: {

                float x = event.getX();
                float y = event.getY();
                break;

            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;

        }
        return true;
    }


    private int mazeWidth, mazeHeight;
    private int cellWidth, cellHeight;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
// Account for padding
        int xpad = getPaddingLeft() + getPaddingRight();
        int ypad = getPaddingTop() + getPaddingBottom();
        mazeWidth = w - xpad;
        mazeHeight =h - ypad;

    }
}
