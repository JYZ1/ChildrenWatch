package com.jyz.childrenwatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jyz.childrenwatch.activity.reg.FillVerificationActivity;

/**
 * Created by Administrator on 2018/1/2.
 */

public class RelativesActivity extends AppCompatActivity {
    private Button mOther;
    private Button mBabyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatives );

        mBabyBtn = (Button) findViewById(R.id.button);
        mBabyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RelativesActivity.this, AddToActivity.class);
                startActivity(intent);
            }
        });

        mOther = (Button) findViewById(R.id.other);
        mOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopUpBoxActivity box = new PopUpBoxActivity();

                box.create(RelativesActivity.this,"输入亲属关系", "请输入亲属关系", "取消", "确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        box.dismiss();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (box.isEditBoxNull() == true) {
                            Toast.makeText(RelativesActivity.this, R.string.please_enter, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent intent = new Intent(RelativesActivity.this, FillVerificationActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                box.show();
            }
        });
    }
}
