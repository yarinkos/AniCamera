
package com.example.yarinkossover.snapapp.views.smallviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import lombok.Getter;
import lombok.Setter;

//taken from : http://stackoverflow.com/questions/29381474/how-to-draw-a-circle-with-animation-in-android
public class Circle extends View {

    private static final int START_ANGLE_POINT = -90;

    private final Paint paint, whitePaint, paintCircle;
    private RectF rect;

    private float angle;
    final int strokeWidth = 20;
    final int strokeWidthWhite = 20;

    boolean alreadySetBaseRect = false;
    @Getter
    @Setter
    boolean drawBigCircle = false;

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);


        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        //Circle color
        paint.setColor(Color.RED);

        whitePaint = new Paint();
        whitePaint.setAntiAlias(true);
        whitePaint.setStyle(Paint.Style.STROKE);
        whitePaint.setStrokeWidth(strokeWidthWhite);
        //Circle color
        whitePaint.setColor(Color.WHITE);


        paintCircle = new Paint();
        paintCircle.setAntiAlias(true);
        paintCircle.setStyle(Paint.Style.FILL);
        //Circle color
        paintCircle.setColor(Color.RED);

        //size 200x200 example todo refactor value out of this class
        //rect = new RectF(strokeWidth, strokeWidth, 610 + strokeWidth, 610 + strokeWidth);
        // rect = new RectF(0, 0, 300 /*+ strokeWidth*/, 300 /*+ strokeWidth*/);
        rect = new RectF();
        //Initial Angle (optional, it can be zero)
        angle = 0;

    }

    public void setRect(int width) {
        rect = new RectF(strokeWidth, strokeWidth, width + strokeWidth, width + strokeWidth);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!alreadySetBaseRect) {
            rect.set(strokeWidth, strokeWidth, getWidth() - strokeWidth, getHeight() - strokeWidth);
            alreadySetBaseRect = !alreadySetBaseRect;
        }
        canvas.drawArc(rect, START_ANGLE_POINT, 360, false, whitePaint);
        canvas.drawArc(rect, START_ANGLE_POINT, angle, false, paint);
        if (drawBigCircle) {
            canvas.drawCircle(getWidth() / 2, getWidth() / 2, getWidth() / 3, paintCircle);
        }
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }



}