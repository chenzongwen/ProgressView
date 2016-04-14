package com.owenchan.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.owenchan.demo.R;

/**
 * Created by Owen Chan on 16/4/14.
 * Copyright © 2016 Owen Chan. All rights reserved.
 */
public class RingProgressView extends View {

    private float mPercent = 75;
    private float mStrokeWidth;
    private int mBgColor = 0xffe1e1e1;
    private float mStartAngle = 0;
    private int mFgColorStart = 0xffffe400;
    private int mFgColorEnd = 0xffff4800;

    private LinearGradient mShader;
    private Context mContext;
    private RectF mOval;
    private Paint mPaint;


    public RingProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.RingProgressView, 0,0);

        try {
            mBgColor = a.getColor(R.styleable.RingProgressView_background_color, 0xffe1e1e1);
            mFgColorEnd = a.getColor(R.styleable.RingProgressView_fgColorEnd, 0xffff4800);

            mFgColorStart = a.getColor(R.styleable.RingProgressView_fgColorStart, 0xffffe400);
            mPercent = a.getFloat(R.styleable.RingProgressView_proportion, 75);
            mStartAngle = a.getFloat(R.styleable.RingProgressView_start_position, 0)+270;
            mStrokeWidth = a.getDimensionPixelSize(R.styleable.RingProgressView_strokeWidth, dp2px(21));
        } finally {
            a.recycle();
        }

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }



    private int dp2px(float dp) {
        return (int) (mContext.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setShader(null);
        mPaint.setColor(mBgColor);
        canvas.drawArc(mOval, 0, 360, false, mPaint);

        mPaint.setShader(mShader);
        canvas.drawArc(mOval, mStartAngle, mPercent * 3.6f, false, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resizeOval();

        mShader = new LinearGradient(mOval.left, mOval.top,
                mOval.left, mOval.bottom, mFgColorStart, mFgColorEnd, Shader.TileMode.MIRROR);
    }

    private void resizeOval() {
        int xp = getPaddingLeft() + getPaddingRight();
        int yp = getPaddingBottom() + getPaddingTop();
        mOval = new RectF(getPaddingLeft() + mStrokeWidth, getPaddingTop() + mStrokeWidth,
                getPaddingLeft() + (getWidth() - xp) - mStrokeWidth,
                getPaddingTop() + (getHeight() - yp) - mStrokeWidth);
    }

    public void refreshView() {
        invalidate();
        requestLayout();
    }



    public void setStrokeWidthDp(float dp) {
        this.mStrokeWidth = dp2px(dp);
        mPaint.setStrokeWidth(mStrokeWidth);
        resizeOval();
        refreshView();
    }

    public void setPercent(float mPercent) {
        this.mPercent = mPercent;
        refreshView();
    }

    public void setFgColorStart(int mFgColorStart) {
        this.mFgColorStart = mFgColorStart;
        mShader = new LinearGradient(mOval.left, mOval.top,
                mOval.left, mOval.bottom, mFgColorStart, mFgColorEnd, Shader.TileMode.MIRROR);
        refreshView();
    }


    public void setStartAngle(float mStartAngle) {
        this.mStartAngle = mStartAngle + 270;
        refreshView();
    }
}
