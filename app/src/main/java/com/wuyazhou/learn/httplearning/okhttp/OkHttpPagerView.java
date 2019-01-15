package com.wuyazhou.learn.httplearning.okhttp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wuyazhou.learn.httplearning.MainActivity;
import com.wuyazhou.learn.httplearning.R;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * @author 吴亚洲
 * @date 2018.7.7
 * @function
 */
public class OkHttpPagerView extends FrameLayout implements View.OnClickListener {
    private Context mContext = null;
    private LinearLayout mLayout;

    private TextView mResultView;
    private ImageView mImageView;

    private OkHttpClient mOkHttpClient = null;

    public OkHttpPagerView(Context context) {
        super(context);
        mContext = context;
        initData();
        initView();
    }

    public OkHttpPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initData();
        initView();
    }

    public OkHttpPagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initData();
        initView();
    }

    private void initData() {
        File sdCache = mContext.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder().
                connectTimeout(15,TimeUnit.SECONDS).
                writeTimeout(20,TimeUnit.SECONDS).
                readTimeout(20,TimeUnit.SECONDS).
                cache(new Cache(sdCache.getAbsoluteFile(),cacheSize));
        mOkHttpClient = okHttpClientBuilder.build();
    }


    public void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = (LinearLayout) inflater.inflate(R.layout.pager_okhttp_layout, null);

        addView(mLayout);

        View getRequestButton  = mLayout.findViewById(R.id.get_request_button);
        getRequestButton.setOnClickListener(this);

        View postRequestButton  = mLayout.findViewById(R.id.post_request_button);
        postRequestButton.setOnClickListener(this);

        View postFileRequestButton  = mLayout.findViewById(R.id.post_file_request_button);
        postFileRequestButton.setOnClickListener(this);

        View getImageRequestButtonOne  = mLayout.findViewById(R.id.get_image_request_button_1);
        getImageRequestButtonOne.setOnClickListener(this);

        View getImageRequestButtonTwo  = mLayout.findViewById(R.id.get_image_request_button_2);
        getImageRequestButtonTwo.setOnClickListener(this);

        View postMultipartRequestButton  = mLayout.findViewById(R.id.post_file_and_key_value_request_button);
        postMultipartRequestButton.setOnClickListener(this);

        mResultView = mLayout.findViewById(R.id.result);

        mImageView = mLayout.findViewById(R.id.image_view);
    }

    @Override
    public void onClick(View v) {
        mResultView.setVisibility(GONE);
        mImageView.setVisibility(GONE);
        switch (v.getId()){
            case R.id.get_request_button:
                mResultView.setVisibility(VISIBLE);
                OkHttpTest.getRequest(mOkHttpClient, MainActivity.BAI_DU,mResultView);
                break;
            case R.id.post_request_button:
                mResultView.setVisibility(VISIBLE);
                OkHttpTest.postRequest(mOkHttpClient, MainActivity.TAO_BAO_HTTP,mResultView);
                break;
            case R.id.post_file_request_button:
                mResultView.setVisibility(VISIBLE);
                OkHttpTest.postFileRequest(mOkHttpClient, MainActivity.UPLOAD_FILE_HTTP,mResultView);
                break;
            case R.id.get_image_request_button_1:
                mImageView.setVisibility(VISIBLE);
                OkHttpTest.getImageRequest(mOkHttpClient, MainActivity.IMAGE_URL_1,mImageView);
                break;
            case R.id.get_image_request_button_2:
                mImageView.setVisibility(VISIBLE);
                OkHttpTest.getImageRequest(mOkHttpClient, MainActivity.IMAGE_URL_2,mImageView);
                break;
            case R.id.post_file_and_key_value_request_button:
                mResultView.setVisibility(VISIBLE);
                OkHttpTest.postMultipartRequest(mOkHttpClient, MainActivity.TAO_BAO_HTTP,mResultView);
                break;
            default:
                break;
        }
    }
}
