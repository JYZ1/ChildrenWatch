package com.jyz.childrenwatch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class PositioningActivity extends FragmentActivity implements View.OnClickListener {
    //声明ViewPager
    private ViewPager mViewPager;

    //适配器
    private FragmentPagerAdapter mAdapter;
    //装载Fragment的集合
    private List<Fragment> mFragments;

    //四个Tab对应的布局
    private LinearLayout mTabPosition;
    private LinearLayout mTabNews;
    private LinearLayout mTabSet;

    //四个Tab对应的ImageButton
    private ImageButton mImgPositin;
    private ImageButton mImgNews;
    private ImageButton mImgSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_positioning );

        initViews();//初始化控件
        initEvents();//初始化事件
        initDatas();//初始化数据
        mImgPositin.setImageResource(R.drawable.dl);
    }

    private void initDatas() {
        mFragments = new ArrayList<>();
        //将三个Fragment加入集合中
        //mFragments.add(new PositioningFragment());
        mFragments.add(new GaoDeFragment());
        mFragments.add(new NewsFragment());
        mFragments.add(new SetFragment());

        //初始化适配器
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {//从集合中获取对应位置的Fragment
                return mFragments.get(position);
            }

            @Override
            public int getCount() {//获取集合中Fragment的总数
                return mFragments.size();
            }
        };
        //不要忘记设置ViewPager的适配器
        mViewPager.setAdapter(mAdapter);
        //设置ViewPager的切换监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            //页面滚动事件
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //页面选中事件
            @Override
            public void onPageSelected(int position) {
                //设置position对应的集合中的Fragment
                mViewPager.setCurrentItem(position);
                resetImgs();
                selectTab(position);
            }

            @Override
            //页面滚动状态改变事件
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initEvents() {
        //设置四个Tab的点击事件
        mTabPosition.setOnClickListener(this);
        mTabNews.setOnClickListener(this);
        mTabSet.setOnClickListener(this);
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mTabPosition = (LinearLayout) findViewById(R.id.id_tab_weixin);
        mTabNews = (LinearLayout) findViewById(R.id.id_tab_frd);
        mTabSet = (LinearLayout) findViewById(R.id.id_tab_address);

        mImgPositin = (ImageButton) findViewById(R.id.mapView);
        mImgNews = (ImageButton) findViewById(R.id.id_tab_frd_img);
        mImgSet = (ImageButton) findViewById(R.id.id_tab_address_img);
    }

    @Override
    public void onClick(View v) {
        //先将四个ImageButton置为灰色
        resetImgs();

        //根据点击的Tab切换不同的页面及设置对应的ImageButton为绿色
        switch (v.getId()) {
            case R.id.id_tab_weixin:
                selectTab(0);
                break;
            case R.id.id_tab_frd:
                selectTab(1);
                break;
            case R.id.id_tab_address:
                selectTab(2);
                break;
        }
    }

    private void selectTab(int i) {
        //根据点击的Tab设置对应的ImageButton为绿色
        switch (i) {
            case 0:
                mImgPositin.setImageResource(R.drawable.dl);
                break;
            case 1:
                mImgNews.setImageResource(R.drawable.ll);
                break;
            case 2:
                mImgSet.setImageResource(R.drawable.wl);
                break;
        }
        //设置当前点击的Tab所对应的页面
        mViewPager.setCurrentItem(i);
    }

    //将三个ImageButton设置为灰色
    private void resetImgs() {
        mImgPositin.setImageResource(R.drawable.d);
        mImgNews.setImageResource(R.drawable.l);
        mImgSet.setImageResource(R.drawable.p);
    }
}
