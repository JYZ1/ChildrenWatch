package com.jyz.childrenwatch.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jyz.childrenwatch.R;

/**
 * Created by Administrator on 2017/12/27.
 */

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText mPhoneNumber;
    private Button mCarryOut;

    private final int PHONE_NUMBER = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword );

        mPhoneNumber = (EditText) findViewById(R.id.phoneNumber);


        mCarryOut = (Button) findViewById(R.id.carry_out);
        mCarryOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPhoneNumber.getText().length() == PHONE_NUMBER)
                {
                    String edit = mPhoneNumber.getText().toString();
                    Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                    intent.putExtra("phoneNumber", edit);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ForgetPasswordActivity.this, R.string.Phone_number_is_11_digit, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
