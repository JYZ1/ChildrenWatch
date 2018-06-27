package com.jyz.childrenwatch;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/1/4.
 */

public class PopUpBoxActivity {
    private Dialog mDialog;
    private  EditText mEdit;
    private TextView mTitle;
    private Button mCancelBtn;
    private Button mComfirmBtn;

    public void create (Context ctx,String title, String text, String leftButton, String rightButton, View.OnClickListener listenerLeft, View.OnClickListener listenerRight) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        View view = View
                .inflate(ctx, R.layout.activity_popupbox, null);
        builder.setView(view);

        mTitle = (TextView)view.findViewById(R.id.title);
        mTitle.setText(title);

        mEdit = (EditText)view.findViewById(R.id.dialog_edit);
        mEdit.setHint(text);
        mCancelBtn = (Button)view.findViewById(R.id.btn_cancel);
        mCancelBtn.setOnClickListener(listenerLeft);

        mComfirmBtn = (Button)view.findViewById(R.id.btn_comfirm);
        mComfirmBtn.setOnClickListener(listenerRight);

        mDialog = builder.create();
    }

    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public boolean isEditBoxNull() {
        if (mEdit.getText().length() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isEditBoxBa() {
        if (mEdit.getText().length() == 8) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isEditBoxShiYi() {
        if (mEdit.getText().length() == 11) {
            return true;
        }
        else {
            return false;
        }
    }

    public String transfer() {
        return mEdit.getText().toString();
    }
}




