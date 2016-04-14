package com.owenchan.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.owenchan.demo.R;
import com.owenchan.demo.view.SectorProgressView;

/**
 * Created by Owen Chan on 16/4/13.
 * Copyright © 2016 Owen Chan. All rights reserved.
 */
public class SectorActivity extends Activity {

    private SectorProgressView mSectorProgressView;
    private SeekBar startPosBar;
    private SeekBar proportionBar;
    private TextView barInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sector);
        initView();
    }

    private void initView() {

        barInfo = (TextView) findViewById(R.id.bar_info);
        mSectorProgressView = (SectorProgressView) findViewById(R.id.sector_progress_view);

        startPosBar = (SeekBar) findViewById(R.id.start_pos_seek_bar);
        proportionBar = (SeekBar) findViewById(R.id.proportion_seek_bar);

        startPosBar.setOnSeekBarChangeListener(mOnSeekBarChangeListener);
        proportionBar.setOnSeekBarChangeListener(mOnSeekBarChangeListener);

        barInfo.setText("startPos: " + startPosBar.getProgress() + ";  proportion: " + proportionBar.getProgress());
    }





    private OnSeekBarChangeListener mOnSeekBarChangeListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()) {
                case R.id.proportion_seek_bar:
                   mSectorProgressView.setProportion(progress);
                    break;
                case R.id.start_pos_seek_bar:
                    mSectorProgressView.setStartPos(progress);
                    break;
            }
            barInfo.setText("startPos: " + startPosBar.getProgress() + ";  proportion: " + proportionBar.getProgress());
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
