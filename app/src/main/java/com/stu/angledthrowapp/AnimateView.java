package com.stu.angledthrowapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

public class AnimateView extends AppCompatImageView {

    private Context mContext;
    private int level = 900;
    private Handler h;
    private BitmapDrawable bitmapDrawable;
    private final int FRAME_RATE = 30;
    private Paint paint = new Paint();
    private BitmapDrawable ball;

    int x = 0;
    int y = level;
    int i = 0;

    public AnimateView(@NonNull Context context, AttributeSet attributeSets) {
        super(context, attributeSets);
        mContext = context;
        h = new Handler();
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.star);
        ball = new BitmapDrawable(context.getResources(), bitmap);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3f);
        paint.setStyle(Paint.Style.FILL);
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    protected void onDraw(Canvas c) {
        if (AngledThrowCalculator.lastChange == 1) {
            if (i < AngledThrowCalculator.xCoords.size() - 1) {
                x = (int) AngledThrowCalculator.xCoords.get(i).intValue();
                y = level - AngledThrowCalculator.yCoords.get(i).intValue();
                i++;
            }
            c.drawBitmap(ball.getBitmap(), x, y, null);
            c.drawLine(0, level + 85, getWidth(), level + 85, paint);
            if(AngledThrowCalculator.xCoords.get(AngledThrowCalculator.xCoords.size()-1)<80){
                paint.setTextSize(24f);
            }else{
                paint.setTextSize(48f);
                c.drawText("0",40,level+130,paint);
            }
            c.drawText(AngledThrowCalculator.xCoords.get(AngledThrowCalculator.xCoords.size()-1).toString(),
                    AngledThrowCalculator.xCoords.get(AngledThrowCalculator.xCoords.size()-1).floatValue(),
                    level+130,paint);

            h.postDelayed(r, FRAME_RATE);
        } else {
            if (i < AngledThrowCalculator.responseAngledThrow.getCoords().size() - 1) {
                x = (int) AngledThrowCalculator.responseAngledThrow.getCoords().get(i).getX().intValue();
                y = level - AngledThrowCalculator.responseAngledThrow.getCoords().get(i).getY().intValue();
                i++;
            }
            c.drawBitmap(ball.getBitmap(), x, y, null);
            c.drawLine(0, level + 140, getWidth(), level + 140, paint);

            h.postDelayed(r, FRAME_RATE);
        }
    }
}
