package com.jyz.childrenwatch;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.jyz.childrenwatch.R;

public class ErrorMessage {
    public static final String IS_NOT_REGISTERED = "-1";
    public static final String NETWORK_ERROR = "-16";

    public static void showMessage(final Context context, String errorCode) {
        int resId = 0;
        switch (errorCode) {
            case IS_NOT_REGISTERED:
                resId = R.string.Account_registered;
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
