package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.MotionEventCompat;

public class JoyStickView extends View {
    private Paint colors =new Paint();
    private boolean circleBound;
    private float currentX;
    private float currentY;

    public JoyStickView(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        colors.setColor(Color.GRAY);
        canvas.drawCircle(getWidth()/2, getHeight()/2, 390, colors);
        colors.setColor(Color.BLACK);
        canvas.drawCircle(currentX,currentY,130, colors);
    }

    @SuppressWarnings("deprecation")
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                if(InCircle(event.getX(), event.getY()))
                    circleBound = true;
                return true;
            }
            case MotionEvent.ACTION_MOVE: {

              if (!circleBound)
                  return true;
              if (inOuterBounds(event.getX(), event.getY())) {
                    currentX = event.getX();
                    currentY = event.getY();
                    invalidate();
              }
                return true;
            }
            case MotionEvent.ACTION_UP: {
                circleBound = false;
                goToDefaultPosition();
                invalidate();
                return true;
            }
        }
        return true;
    }

    private void goToDefaultPosition() {
        currentX =  getWidth() / 2;
        currentY =  getHeight() / 2;
    }

    private boolean inOuterBounds(float x, float y) {
        double distanceFromCenter = Math.sqrt((getWidth()/2 - x) * (getWidth()/2 - x) + (getHeight()/2 - y) * (getHeight()/2 - y));
        return (distanceFromCenter <= 260);

    }

    private boolean InCircle(float x, float y) {
        double distanceFromCenter = Math.sqrt((getWidth()/2 - x) * (getWidth()/2 - x) + (getHeight()/2 - y) * (getHeight()/2 - y));
        return (distanceFromCenter <= 130);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        currentX = getWidth()/2;
        currentY = getHeight()/2;
    }
}
