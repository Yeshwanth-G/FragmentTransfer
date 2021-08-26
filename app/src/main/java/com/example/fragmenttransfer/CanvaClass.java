package com.example.fragmenttransfer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class CanvaClass extends View {
    Paint paint;
    Path path;
    public CanvaClass(Context context) {
        super(context);
        paint=new Paint();
        path=new Path();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                return  true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x,y);
            case MotionEvent.ACTION_POINTER_UP:
                break;
            default:
                return  false;
        }
        invalidate();
        return true;
    }
}
