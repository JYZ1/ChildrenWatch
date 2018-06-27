package com.jyz.childrenwatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2018/6/26.
 */

public class BabyHWActivity extends AppCompatActivity {
    private Button mSubmit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shw);

        mSubmit_btn = (Button) findViewById(R.id.submit_btn);
        mSubmit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BabyHWActivity.this, BabyInformationActivity.class);
                startActivity(intent);
            }
        });
    }
}
