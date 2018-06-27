package com.jyz.childrenwatch.activity.reg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jyz.childrenwatch.Entity.RegsterEn;
import com.jyz.childrenwatch.Entity.SmsEn;
import com.jyz.childrenwatch.Entity.SmsResponse;
import com.jyz.childrenwatch.LoginActivity;
import com.jyz.childrenwatch.R;
import com.jyz.childrenwatch.constants.UrlConstants;
import com.jyz.childrenwatch.net.HttpCallbackListener;
import com.jyz.childrenwatch.net.HttpUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/12/29.
 */

public class FillInActivity extends AppCompatActivity {
    private final int VCODE_LEN = 4;
    private final int PASSWD_LEN = 6;
    private final int TWENTY = 20;
    private final int MAX_TIME = 60;
    private TextView mNumText;
    private TimerTask timerTask;
    private Button countdown;
    private Button mCreate;
    private EditText smsEdit;
    private EditText mPasEdit;
    private Button fillIn;

    private String mPhoneNum;
    private String vCode;

    private int count = MAX_TIME;
    Timer timer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillin);

        final Intent intent = getIntent();
        mPhoneNum = intent.getStringExtra("phone");

        mNumText = (TextView) findViewById(R.id.numText);
        if (mPhoneNum != null) {
            mNumText.setText(mPhoneNum);
        }

        countdown = (Button) findViewById(R.id.countdown);
        mCreate = (Button) findViewById(R.id.create);

        smsEdit = (EditText) findViewById(R.id.smsEdit);
        mPasEdit = (EditText) findViewById(R.id.pasEdit);


        countdown.setClickable(false);
        startTimer();
        getVerCode();


        countdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
                countdown.setEnabled(false);
            }
        });

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String passWd = mPasEdit.getText().toString();
                if (smsEdit.getText().length() != VCODE_LEN) {
                    Toast.makeText(FillInActivity.this, R.string.Verification_code_is_4_digits, Toast.LENGTH_SHORT).show();
                }
                if (mPasEdit.getText().length() < PASSWD_LEN && mPasEdit.getText().length() > TWENTY) {
                    Toast.makeText(FillInActivity.this, R.string.Password_length_must_be_greater_than_six_digits_and_less_than_twenty_digits, Toast.LENGTH_SHORT).show();
                }

                RegsterEn regsterEn = new RegsterEn();
                regsterEn.setUserAccount(Long.valueOf(mPhoneNum));
                regsterEn.setUserPassword(passWd);
                regsterEn.setUserName("");
                regsterEn.setProtocolVer("0");

                final Gson gson = new Gson();
                String data = gson.toJson(regsterEn);
                HttpUtil.sendHttpRequest(UrlConstants.REGISTER_URL, data, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        SmsResponse smsResponse = gson.fromJson(response, SmsResponse.class);

                        if (smsResponse.getRetFlag().equals("1")) {
                            Intent intent = new Intent(FillInActivity.this, LoginActivity.class);
                            intent.putExtra("phone", mPhoneNum);
                            startActivity(intent);
                        } else if (smsResponse.getRetFlag().equals("0")) {
                            Toast.makeText(FillInActivity.this, R.string.registration_failed, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }

            //}
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
                countdown.setText(time);

                if (time.equals("重新获取")) {
                    countdown.setEnabled(true);
                }
            }
        });
    }

    public void getVerCode() {
        SmsEn smsEn = new SmsEn();
        smsEn.setPhoneNo(mPhoneNum);
        smsEn.setSmsType(1);
        smsEn.setContent("");

        final Gson gson = new Gson();
        String data = gson.toJson(smsEn);
        HttpUtil.sendHttpRequest(UrlConstants.SEND_SMS_URL, data, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                if (null != response) {
                    SmsResponse smsResponse = gson.fromJson(response, SmsResponse.class);

                    if (smsResponse.getRetFlag().equals("0")) {
                        Toast.makeText(FillInActivity.this, R.string.Failed_to_get_verification_code, Toast.LENGTH_SHORT).show();
                    } else {
                        vCode = smsResponse.getResult().getvCode();
                    }
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}
