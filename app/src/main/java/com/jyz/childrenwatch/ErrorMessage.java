package com.jyz.childrenwatch;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.jyz.childrenwatch.R;

public class ErrorMessage {
    public static final String SUCCESS = "1";
    public static final String FAIL = "0";
    public static final String ACCOUNT_REGISTERED = "-1";
    public static final String ACCOUNT_NOT_REGISTERED = "-2";
    public static final String ACCOUNT_PASSWD_ERROR = "-3";
    public static final String NETWORK_ERROR = "-16";

    public static void showMessage(final Context context, String errorCode) {
        int resId = 0;
        switch (errorCode) {
            case ACCOUNT_REGISTERED:
                resId = R.string.Account_registered;
                break;
            case ACCOUNT_NOT_REGISTERED:
                resId = R.string.account_not_reg;
                break;
            case ACCOUNT_PASSWD_ERROR:
                resId = R.string.account_passwd_error;
                break;
            case NETWORK_ERROR:
                resId = R.string.network_error;
                break;
            default:
                resId = 0;
                break;
        }

        if(0 != resId) {
            show(context, resId);
        }
    }

    private static void show(final Context context, final int resId) {
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
