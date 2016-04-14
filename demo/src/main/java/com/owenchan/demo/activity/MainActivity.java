package com.owenchan.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.owenchan.demo.R;

public class MainActivity extends Activity implements View.OnClickListener {

    TextView sectorButton;
    TextView ringButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        sectorButton = (TextView) findViewById(R.id.sector_progress);
        ringButton = (TextView) findViewById(R.id.ring_progress);

        sectorButton.setOnClickListener(this);
        ringButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sector_progress:
                Intent mIntent = new Intent(MainActivity.this, SectorActivity.class);
                startActivity(mIntent);
                break;
            case R.id.ring_progress:
                Intent mmIntent = new Intent(MainActivity.this, RingActivity.class);
                startActivity(mmIntent);
                break;
            default:
                // do nothing
        }

    }
}
