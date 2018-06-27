package com.jyz.childrenwatch.activity.reg;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jyz.childrenwatch.R;
import com.jyz.childrenwatch.ErrorMessage;
import com.jyz.childrenwatch.dto.BaseDto;
import com.jyz.childrenwatch.json.CommResponse;
import com.jyz.childrenwatch.net.HttpCallbackListener;
import com.jyz.childrenwatch.net.HttpUtil;
import com.jyz.childrenwatch.constants.UrlConstants;

/**
 * Created by Administrator on 2017/12/27.
 */

public class AccountInputActivity extends AppCompatActivity {
    private static final int PHONE_NUMBER_LEN = 11;
    private EditText mAccountEidt;
    private Button mNextBtn;
    private CheckBox mAgreeChb;
    private TextView mUserVerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accout_input);

        mAccountEidt = (EditText)findViewById(R.id.account_edit);

        mNextBtn = (Button)findViewById(R.id.next_btn);
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String account = mAccountEidt.getText().toString();

                if (account.length() < PHONE_NUMBER_LEN) {
                    Toast.makeText(AccountInputActivity.this, R.string.Phone_number_is_11_digit, Toast.LENGTH_SHORT).show();
                    return;
                }

                BaseDto baseDto = new BaseDto();
                baseDto.setUserAccount(Long.valueOf(account));
                baseDto.setProtocolVer("0");
                final Gson gson = new Gson();
                String data = gson.toJson(baseDto);

                HttpUtil.sendHttpRequest(UrlConstants.ISREGISTERED_URL, data, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        if (null != response) {
                            CommResponse commResponse = gson.fromJson(response, CommResponse.class);
                            String retFlag = commResponse.getRetFlag();

                            if (retFlag.equals(ErrorMessage.SUCCESS)) {
                                Intent intent = new Intent(AccountInputActivity.this, FillInActivity.class);
                                intent.putExtra("phone", account);
                                startActivity(intent);
                            } else {
                                ErrorMessage.showMessage(AccountInputActivity.this, retFlag);
                            }
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               ErrorMessage.showMessage(AccountInputActivity.this, ErrorMessage.NETWORK_ERROR);
                            }
                        });
                    }
                });
            }
        });

        mAgreeChb = (CheckBox)findViewById(R.id.agree_chb);
        mAgreeChb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mAgreeChb.isChecked()) {
                    mNextBtn.setEnabled(true);
                } else {
                    mNextBtn.setEnabled(false);
                }
            }
        });

        mUserVerText = (TextView)findViewById(R.id.user_ver_text);
        mUserVerText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mUserVerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountInputActivity.this, UserAgreementActivity.class);
                startActivity(intent);
            }
        });

    }
}


