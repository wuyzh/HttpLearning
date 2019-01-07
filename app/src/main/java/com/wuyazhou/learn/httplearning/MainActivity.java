package com.wuyazhou.learn.httplearning;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wuyazhou.learn.httplearning.ViewPager.PagerOneView;
import com.wuyazhou.learn.httplearning.volley.PagerViewVolley;
import com.wuyazhou.learn.httplearning.ViewPager.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuyazhou
 *
 * */
public class MainActivity extends AppCompatActivity {
    public static final String BAIDU = "https://www.baidu.com";
    public static final String TAOBAO = "http://api.k780.com:88/?app=phone.get&phone=13800138000&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
    //public static final String TAOBAO = "http://ip.taobao.com/service/getIpInfo.php?ip=59.108.54.37";
    public static final String IMAGEURL = "https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_jpg/trm5VMeFp9mJejJH2asZZT0ML63erOW3QAMSkjEMsLwByykbJwsHj7QmPbQDDUU43BJpHTXxyiaY24LXlA6zKDQ/640?wx_fmt=jpeg";


    private ViewPager mViewPager = null;

    private List<View> mViews = new ArrayList<View>();
    private List<String> mViewTitle = new ArrayList<String>();
    private ViewPagerAdapter mViewPagerAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPager();
    }

    private void initPager(){
        mViewPager = findViewById(R.id.view_pager);
        mViewPagerAdapter = new ViewPagerAdapter(mViews,mViewTitle, this);
        mViewPager.setAdapter(mViewPagerAdapter);
        addViewPagerView("volley",new PagerViewVolley(this));
        addViewPagerView("待使用",new PagerOneView(this));
        mViewPagerAdapter.notifyDataSetChanged();
    }

    private void addViewPagerView(String title, View view){
        mViewTitle.add(title);
        mViews.add(view);
    }
}
