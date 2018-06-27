package com.jyz.childrenwatch.activity.reg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jyz.childrenwatch.LoginActivity;
import com.jyz.childrenwatch.R;

import java.util.Timer;
import java.util.TimerTask;
/**
 * Created by Administrator on 2018/1/5.
 */

public class FillVerificationActivity extends AppCompatActivity {

    private TimerTask timerTask;
    private EditText edit;
    private Button buttonTime;
    private Button countBtn;

    private final int MAX_TIME = 60;
    private int count = MAX_TIME;
    private final int FOUR_NUMBER = 4;
    Timer timer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillverification );

        edit = (EditText) findViewById(R.id.edit);
        buttonTime = (Button) findViewById(R.id.buttontime);
        countBtn = (Button) findViewById(R.id.count);
        buttonTime.setEnabled(false);
        startTimer();
        FillInActivity fillInActivity = new FillInActivity();
        fillInActivity.getVerCode();

        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
                buttonTime.setEnabled(false);

            }
        });

        countBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit.getText().length()  == FOUR_NUMBER) {
                    Intent intent = new Intent(FillVerificationActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(FillVerificationActivity.this, R.string.Unverifiable , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void startTimer() {
        timer = new Timer();
        count = MAX_TIME;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count--;
                if (count > 0) {
                    showTime(count + "s");
                } else {
                    timer.cancel();
                    timer = null;

                    showTime("重新获取");
                }
            }
        }, 1000, 1000);
    }

    private void showTime(final String time) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                buttonTime.setText(time);

                if (time.equals("重新获取")) {
                    buttonTime.setEnabled(true);
                }
            }
        });
    }
}
