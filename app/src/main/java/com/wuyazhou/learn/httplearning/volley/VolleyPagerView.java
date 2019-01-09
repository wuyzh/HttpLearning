package com.wuyazhou.learn.httplearning.volley;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.wuyazhou.learn.httplearning.MainActivity;
import com.wuyazhou.learn.httplearning.R;

/**
 * @author 吴亚洲
 * @date 2018.7.7
 * @function
 */
public class VolleyPagerView extends FrameLayout implements View.OnClickListener {
    private Context mContext = null;
    private LinearLayout mLayout;

    private RequestQueue mQueue;

    private TextView mResultView;
    private NetworkImageView mNetworkImageView;
    private ImageView mImageView;

    public VolleyPagerView(Context context) {
        super(context);
        mContext = context;
        initData();
        initView();
    }

    public VolleyPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initData();
        initView();
    }

    public VolleyPagerView(Context context, AttributeSet attrs, int defStyleAttr) {
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

        View imageRequest = mLayout.findViewById(R.id.image_request);
        imageRequest.setOnClickListener(this);

        View imageLoader = mLayout.findViewById(R.id.image_loader);
        imageLoader.setOnClickListener(this);

        View networkImageViewRequestOne = mLayout.findViewById(R.id.network_image_view_button_1);
        networkImageViewRequestOne.setOnClickListener(this);

        View networkImageViewRequestTwo = mLayout.findViewById(R.id.network_image_view_button_2);
        networkImageViewRequestTwo.setOnClickListener(this);

        mResultView = mLayout.findViewById(R.id.result);

        mImageView = mLayout.findViewById(R.id.image_view);

        mNetworkImageView = mLayout.findViewById(R.id.network_image_view);
    }

    @Override
    public void onClick(View v) {
        mResultView.setText("测试");
        mImageView.setImageBitmap(null);
        mNetworkImageView.setVisibility(GONE);
        switch (v.getId()){
            case R.id.string_request:
                VolleyTest.useVolleyStringRequest(MainActivity.BAI_DU,mQueue,mResultView);
                break;
            case R.id.json_request:
                VolleyTest.useVolleyJsonObjectRequest(MainActivity.TAO_BAO,mQueue,mResultView);
                break;
            case R.id.image_request:
                VolleyTest.useVolleyImageRequest(MainActivity.IMAGE_URL_1,mQueue,mImageView);
                break;
            case R.id.image_loader:
                VolleyTest.useVolleyImageLoader(MainActivity.IMAGE_URL_2,mQueue,mImageView);
                break;
            case R.id.network_image_view_button_1:
                mNetworkImageView.setVisibility(VISIBLE);
                VolleyTest.useVolleyNetWorkImageView(MainActivity.IMAGE_URL_3,mQueue,mNetworkImageView);
                break;
            case R.id.network_image_view_button_2:
                mNetworkImageView.setVisibility(VISIBLE);
                VolleyTest.useVolleyNetWorkImageView(MainActivity.IMAGE_URL_4,mQueue,mNetworkImageView);
                break;
            default:
                break;
        }
    }
}
