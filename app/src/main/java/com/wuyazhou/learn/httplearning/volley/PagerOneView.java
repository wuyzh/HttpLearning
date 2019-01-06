package com.wuyazhou.learn.httplearning.volley;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.wuyazhou.learn.httplearning.R;

/**
 * @author 吴亚洲
 * @date 2018.7.7
 * @function
 */
public class PagerOneView extends FrameLayout {
    private Context mContext = null;
    private RelativeLayout mLayout;

    public PagerOneView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public PagerOneView(Context context,AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public PagerOneView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    public void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = (RelativeLayout) inflater.inflate(R.layout.pager_one_layout, null);

        addView(mLayout);
        //mGoodsList = (ListView) mLayout.findViewById(R.id.lv_goods_list);
    }
}
