package com.jyz.childrenwatch.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.jyz.childrenwatch.R;

/**
 * Created by Administrator on 2018/1/12.
 */

public class SetFragment extends Fragment {
    private ImageButton mFamily_btn;
    private Button mSignOut_btn;
    private ImageButton mPsn_btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3, container, false);

        mPsn_btn = (ImageButton)view.findViewById(R.id.head_btn);
        mPsn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),BabyHWActivity.class));
            }
        });

        mFamily_btn = (ImageButton)view.findViewById(R.id.family_btn);
        mFamily_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),RelativesActivity.class));
            }
        });

        mSignOut_btn = (Button) view.findViewById(R.id.sign_out);
        mSignOut_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
        });

        return view;
    }
}
