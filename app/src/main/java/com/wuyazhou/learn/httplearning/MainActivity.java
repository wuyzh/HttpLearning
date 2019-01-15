package com.wuyazhou.learn.httplearning;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wuyazhou.learn.httplearning.ViewPager.ModelPagerView;
import com.wuyazhou.learn.httplearning.ViewPager.ViewPagerAdapter;
import com.wuyazhou.learn.httplearning.okhttp.OkHttpPagerView;
import com.wuyazhou.learn.httplearning.volley.VolleyPagerView;
import com.wuyazhou.learn.httplearning.volleytheory.VolleyTheoryPagerView;
import com.wuyazhou.learn.logview.LogShowView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuyazhou
 *
 * */
public class MainActivity extends AppCompatActivity {
    public static final String BAI_DU = "https://www.baidu.com";
    public static final String TAO_BAO = "http://api.k780.com:88/?app=phone.get&phone=13800138000&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
    public static final String TAO_BAO_HTTP = "http://api.k780.com:88";
    public static final String UPLOAD_FILE_HTTP = "https://api.github.com/markdown/raw";
    /** public static final String TAO_BAO = "http://ip.taobao.com/service/getIpInfo.php?ip=59.108.54.37";*/
    public static final String IMAGE_URL_1 = "https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_jpg/trm5VMeFp9mJejJH2asZZT0ML63erOW3QAMSkjEMsLwByykbJwsHj7QmPbQDDUU43BJpHTXxyiaY24LXlA6zKDQ/640?wx_fmt=jpeg";
    public static final String IMAGE_URL_2 = "http://imgup01.myra2.com/2017-04/13/11/1492055267740_0.jpg";
    public static final String IMAGE_URL_3 = "http://i1.hexun.com/2017-10-20/191306534.jpg";
    public static final String IMAGE_URL_4 = "http://www.pujia8.com/static/pics/20161116120001_22.jpg";


    private ViewPager mViewPager = null;

    private List<View> mViews = new ArrayList<View>();
    private List<String> mViewTitle = new ArrayList<String>();
    private ViewPagerAdapter mViewPagerAdapter = null;

    private LogShowView mShowLogView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPager();
        initShowLogView();
    }

    private void initPager(){
        mViewPager = findViewById(R.id.view_pager);
        mViewPagerAdapter = new ViewPagerAdapter(mViews,mViewTitle, this);
        mViewPager.setAdapter(mViewPagerAdapter);
        addViewPagerView("Volley",new VolleyPagerView(this));
        addViewPagerView("Volley部分原理测试",new VolleyTheoryPagerView(this));
        addViewPagerView("OkHttp",new OkHttpPagerView(this));
        addViewPagerView("待使用",new ModelPagerView(this));
        mViewPagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(2);
    }

    private void addViewPagerView(String title, View view){
        mViewTitle.add(title);
        mViews.add(view);
    }


    private void initShowLogView(){
        mShowLogView = findViewById(R.id.show_log_view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mShowLogView.release();
    }
}
