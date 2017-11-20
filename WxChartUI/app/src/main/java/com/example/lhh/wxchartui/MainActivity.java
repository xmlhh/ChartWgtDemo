package com.example.lhh.wxchartui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtn;
    private Button mColorBtn;
    private int mFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //[1]
                //Intent intent = new Intent();
                //intent.setClass(MainActivity.this, WXChartActivity.class);
                //startActivity(intent);
                //[2]
                Intent intent = new Intent(MainActivity.this, WXChartActivity.class);
                startActivity(intent);
                //[3]
                //startActivityForResult(new Intent(MainActivity.this, WXChartActivity.class), 1);
            }
        });


        mColorBtn = (Button) findViewById(R.id.changeColorBtn);
        mColorBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switch (mFlag){
                    case 0:
                        mColorBtn.setBackgroundResource(R.color.normal);
                        mFlag = 0;
                        break;

                    case 1:
                        mColorBtn.setBackgroundResource(R.color.select);
                        mFlag = 1;
                        break;

                    default:
                        break;
                }
            }
        });

    }
}
