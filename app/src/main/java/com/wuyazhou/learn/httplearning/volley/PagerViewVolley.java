package com.wuyazhou.learn.httplearning.volley;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.wuyazhou.learn.httplearning.MainActivity;
import com.wuyazhou.learn.httplearning.R;

/**
 * @author 吴亚洲
 * @date 2018.7.7
 * @function
 */
public class PagerViewVolley extends FrameLayout implements View.OnClickListener {
    private Context mContext = null;
    private LinearLayout mLayout;

    private RequestQueue mQueue;

    private TextView mResultView;

    public PagerViewVolley(Context context) {
        super(context);
        mContext = context;
        initData();
        initView();
    }

    public PagerViewVolley(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initData();
        initView();
    }

    public PagerViewVolley(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initData();
        initView();
    }

    private void initData(){
        mQueue = Volley.newRequestQueue(mContext);
    }

    public void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = (LinearLayout) inflater.inflate(R.layout.pager_volley_layout, null);
        addView(mLayout);

        View stringRequest = mLayout.findViewById(R.id.string_request);
        stringRequest.setOnClickListener(this);

        View jsonRequest = mLayout.findViewById(R.id.json_request);
        jsonRequest.setOnClickListener(this);

        mResultView = mLayout.findViewById(R.id.result);
    }

    @Override
    public void onClick(View v) {
        mResultView.setText("测试");
        switch (v.getId()){
            case R.id.string_request:
                VolleyTest.useVolleyStringRequest(MainActivity.BAIDU,mQueue,mResultView);
                break;
            case R.id.json_request:
                VolleyTest.useVolleyJsonObjectRequest(MainActivity.TAOBAO,mQueue,mResultView);
                break;
            default:
                break;
        }
    }
}
