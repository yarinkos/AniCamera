package com.example.yarinkossover.snapapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class SimpleDrawingView extends View {
    // setup initial color
    private int paintColor = Color.YELLOW;
    // defines paint and canvas
    private Paint drawPaint;
    // stores next circle
    ArrayList<Pair<Path,Paint>> paths = new ArrayList<>();

    public SimpleDrawingView(Context context, AttributeSet attrs) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
        paths.add(new Pair<Path, Paint>(new Path(),drawPaint));
    }

    private void setupPaint() {
        // Setup paint with color and stroke styles
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(22);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(Pair pair : paths)
        canvas.drawPath((Path)pair.first, (Paint)pair.second);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();
        // Checks for the event that occurs
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getCurrentPath().moveTo(pointX, pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                getCurrentPath().lineTo(pointX, pointY);
                break;
            default:
                return false;
        }
        // Force a view to draw again
        postInvalidate();
        return true;
    }

    private Path getCurrentPath(){
        return paths.get(paths.size()-1).first;
    }
    public void setPaintColor(int color) {
        this.paintColor = color;
        setupPaint();
        paths.add(new Pair<Path, Paint>(new Path(),drawPaint));
    }

    public void clearPaint(){
        paths.clear();
        paths.add(new Pair<Path, Paint>(new Path(),drawPaint));
    }

}