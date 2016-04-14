package com.owenchan.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.owenchan.demo.R;

/**
 * Created by Owen Chan on 16/4/13.
 * Copyright © 2016 Owen Chan. All rights reserved.
 */
public class SectorProgressView extends View {

    Context mContext;
    private int backgroundColor;
    private int foregroundColor;

    private float proportion;
    private float startPos;

    private Paint bgPaint;
    private Paint fgPaint;

    private RectF oval;

    public SectorProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.SectorProgressView, 0, 0);
        try {
            backgroundColor = a.getColor(R.styleable.SectorProgressView_background_color, 0xffe5e5e5);
            foregroundColor = a.getColor(R.styleable.SectorProgressView_foreground_color, 0xffff765c);
            proportion = a.getFloat(R.styleable.SectorProgressView_proportion, 0);
            startPos = a.getFloat(R.styleable.SectorProgressView_start_position, 0) + 270;
        } finally {
            a.recycle();
        }
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(backgroundColor);

        fgPaint = new Paint();
        fgPaint.setAntiAlias(true);
        fgPaint.setColor(foregroundColor);
    }


    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);

        float  uselessX = (float)(getPaddingLeft() + getPaddingRight());
        float  uselessY = (float)(getPaddingBottom() + getPaddingTop());

        float  useX = (float)width - uselessX;
        float  useY = (float)height - uselessY;

        oval = new RectF(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + useX, getPaddingTop() + useY);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(oval, 0, 360, true, bgPaint);
        canvas.drawArc(oval, startPos, proportion * 3.6f, true, fgPaint);
    }

    public void setProportion(float proportion) {
        this.proportion = proportion;
        invalidate();
        requestLayout();
    }

    public void setStartPos(float startPos) {
        this.startPos = startPos + 270;
        invalidate();
        requestLayout();
    }

}
