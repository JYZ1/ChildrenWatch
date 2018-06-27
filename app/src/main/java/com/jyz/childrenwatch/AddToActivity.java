package com.jyz.childrenwatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/12/29.
 */

public class AddToActivity extends AppCompatActivity {

    private Button mEnter;
    private Button mInto;

    private final int NUMBER = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addto);

        mEnter = (Button) findViewById(R.id.enter);
        mEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddToActivity.this);
                View view = View
                        .inflate(AddToActivity.this, R.layout.activity_popupbox, null);
                builder.setView(view);
                builder.setCancelable(true);
                TextView title= (TextView) view
                        .findViewById(R.id.title);
                final EditText input_edt= (EditText) view
                        .findViewById(R.id.dialog_edit);
                Button btn_cancel=(Button)view
                        .findViewById(R.id.btn_cancel);
                Button btn_comfirm=(Button)view
                        .findViewById(R.id.btn_comfirm);
                        final AlertDialog dialog = builder.show();

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btn_comfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (input_edt.getText().length() == NUMBER) {
                            Intent intent = new Intent(AddToActivity.this, RelativesActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(AddToActivity.this, R.string.Requires_15_digit_DID_number, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mInto = (Button) findViewById(R.id.into);
        mInto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddToActivity.this, PositioningActivity.class);
                startActivity(intent);
            }
        });
    }
}
