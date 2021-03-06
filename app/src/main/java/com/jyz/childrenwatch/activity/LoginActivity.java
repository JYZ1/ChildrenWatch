package com.jyz.childrenwatch.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jyz.childrenwatch.ErrorMessage;
import com.jyz.childrenwatch.dto.BaseDto;
import com.jyz.childrenwatch.json.LoginResponse;
import com.jyz.childrenwatch.R;
import com.jyz.childrenwatch.constants.UrlConstants;
import com.jyz.childrenwatch.net.HttpCallbackListener;
import com.jyz.childrenwatch.net.HttpUtil;
import com.jyz.childrenwatch.activity.reg.AccountInputActivity;

/**
 * Created by Administrator on 2017/12/21.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText mAccountEdit;
    private EditText mPasswordEdit;
    private Button mLogin;
    private Button mForgetPassword;
    private Button mAppid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAccountEdit = (EditText) findViewById(R.id.account);
        mPasswordEdit = (EditText) findViewById(R.id.password);

        mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = mAccountEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();

                BaseDto baseDto = new BaseDto();
                baseDto.setUserAccount(Long.valueOf(account));
                baseDto.setUserPassword(password);
                baseDto.setProtocolVer("0");

                final Gson gson = new Gson();
                String data = gson.toJson(baseDto);
                HttpUtil.sendHttpRequest(UrlConstants.LOGIN_URL, data, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        if(response != null) {
                            LoginResponse loginResponse = gson.fromJson(response, LoginResponse.class);
                            String retFlag = loginResponse.getRetFlag();

                            if(retFlag.equals(ErrorMessage.SUCCESS)) {
                                Intent intent = new Intent(LoginActivity.this, PositioningActivity.class);
                                startActivity(intent);
                            }
                            else{
                                ErrorMessage.showMessage(LoginActivity.this, retFlag);
                            }
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        ErrorMessage.showMessage(LoginActivity.this, ErrorMessage.NETWORK_ERROR);

                    }
                });
            }
        });

        mForgetPassword = (Button) findViewById(R.id.forget_Password);
        mForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

        mAppid = (Button) findViewById(R.id.appleId);
        mAppid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AccountInputActivity.class);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        String number = intent.getStringExtra("phoneNumber");
        if (number != null) {
            mAccountEdit.setText(String.valueOf(number));
        }

        Intent intent2 = getIntent();
        String number2 = intent.getStringExtra("phone");
        if (number2 != null) {
            mAccountEdit.setText(String.valueOf(number2));
        }

        Intent intent3 = getIntent();
        String number3 = intent.getStringExtra("phone");
        if (number3 != null) {
            mAccountEdit.setText(String.valueOf(number3));
        }
    }
}
