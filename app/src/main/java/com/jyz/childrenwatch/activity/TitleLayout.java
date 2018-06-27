package com.jyz.childrenwatch.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jyz.childrenwatch.R;

public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.childrenwatch);
        String backBtnText = typeArray.getString(R.styleable.childrenwatch_backBtnText);

        LayoutInflater.from(context).inflate(R.layout.title, this);
        Button backBtn = (Button)findViewById(R.id.back_btn);
        backBtn.setText(backBtnText);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
    }
}
