package com.wuyazhou.learn.httplearning.okhttp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuyazhou.learn.httplearning.MainActivity;
import com.wuyazhou.learn.httplearning.R;

/**
 * @author 吴亚洲
 * @date 2018.7.7
 * @function
 */
public class OkHttpPagerView extends FrameLayout implements View.OnClickListener {
    private Context mContext = null;
    private LinearLayout mLayout;

    private TextView mResultView;

    public OkHttpPagerView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public OkHttpPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public OkHttpPagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    public void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = (LinearLayout) inflater.inflate(R.layout.pager_okhttp_layout, null);

        addView(mLayout);

        View getRequestButton  = mLayout.findViewById(R.id.get_request_button);
        getRequestButton.setOnClickListener(this);

        View postRequestButton  = mLayout.findViewById(R.id.post_request_button);
        postRequestButton.setOnClickListener(this);

        mResultView = mLayout.findViewById(R.id.result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get_request_button:
                OkHttpTest.getRequest(MainActivity.BAI_DU,mResultView);
                break;
            case R.id.post_request_button:
                OkHttpTest.postRequest(MainActivity.TAO_BAO_HTTP,mResultView);
                break;
            default:
                break;
        }
    }
}
