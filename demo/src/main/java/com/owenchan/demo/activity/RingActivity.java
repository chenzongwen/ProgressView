package com.owenchan.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.owenchan.demo.R;
import com.owenchan.demo.view.RingProgressView;

/**
 * Created by Owen Chan on 16/4/14.
 * Copyright © 2016 Owen Chan. All rights reserved.
 */
public class RingActivity extends Activity {
    private RingProgressView mSectorProgressView;
    private SeekBar startPosBar;
    private SeekBar proportionBar;
    private SeekBar sbStrokeWidth;

    private TextView barInfo;
    private TextView proportion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);
        initView();
    }

    private void initView() {

        proportion = (TextView) findViewById(R.id.proportion);
        mSectorProgressView = (RingProgressView) findViewById(R.id.ring_progress_view);

        startPosBar = (SeekBar) findViewById(R.id.sbPercent);
        proportionBar = (SeekBar) findViewById(R.id.sbStartAngle);
        sbStrokeWidth = (SeekBar) findViewById(R.id.sbStrokeWidth);

        sbStrokeWidth.setOnSeekBarChangeListener(mOnSeekBarChangeListener);
        startPosBar.setOnSeekBarChangeListener(mOnSeekBarChangeListener);
        proportionBar.setOnSeekBarChangeListener(mOnSeekBarChangeListener);

    }

    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()) {
                case R.id.sbPercent:
                    mSectorProgressView.setPercent(progress);
                    proportion.setText(progress + "%");
                    break;
                case R.id.sbStartAngle:
                    mSectorProgressView.setStartAngle(progress);
                    break;
                case R.id.sbStrokeWidth:
                    mSectorProgressView.setStrokeWidthDp(progress);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };




}
