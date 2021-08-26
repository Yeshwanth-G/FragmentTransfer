package com.example.fragmenttransfer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
public class FragmentA extends Fragment  {
    Paint paint;
    Path path,path1;

    RelativeLayout relativeLayout;
    public FragmentAListner listner;
    public interface FragmentAListner{
        void oninputa(Path path);
    }
    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        path=new Path();
        path1=new Path();
        View rootView = inflater.inflate(R.layout.fragment_a, container, false);
        //b=rootView.findViewById(R.id.button);
         relativeLayout = (RelativeLayout) rootView.findViewById(R.id.rect);
        relativeLayout.addView(new Rectangle(getActivity()));
        return rootView;
    }
    public void Update(Path path)
    {
        path1=path;
        relativeLayout.addView(new Rectangle(getActivity()));
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentAListner){
            listner=(FragmentAListner)context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listner=null;
    }

    private class Rectangle extends View {
        Paint paint = new Paint();
       // Path path=new Path();
        public Rectangle(Context context) {
            super(context);
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5f);
        }
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawPath(path1,paint);
            canvas.drawPath(path,paint);
                //canvas.drawPath(path1,paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x=event.getX();
            float y=event.getY();
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(x,y);
                    listner.oninputa(path);
                    return  true;
                case MotionEvent.ACTION_MOVE:
                    path.lineTo(x,y);
                    listner.oninputa(path);
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                default:
                    return  false;
            }
            invalidate();
            return true;
        }
    }
}
