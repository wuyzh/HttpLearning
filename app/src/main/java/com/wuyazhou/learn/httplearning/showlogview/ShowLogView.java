package com.wuyazhou.learn.httplearning.showlogview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.wuyazhou.learn.httplearning.R;

/**
 * @author wuyzh
 * @fuction 展示自己定义的log信息
 * */
public class ShowLogView extends RelativeLayout implements ShowLogViewContract{
    private Context mContext = null;
    private ViewGroup mViewGroup;

    private ShowLogThread mShowLogThread;
    public ShowLogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public ShowLogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShowLogView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewGroup = (ViewGroup) inflater.inflate(R.layout.show_log_layout, null);
        addView(mViewGroup);

        mShowLogThread = new ShowLogThread(ShowLogUtil.getLogQueue());
    }


    @Override
    public void showLog(String string) {
        Log.d("wuyazhouHttp",string);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE){
            if (!mShowLogThread.isAlive()){
                mShowLogThread.start(this);
            }

        }else {
            if (mShowLogThread.isAlive()){
                mShowLogThread.quit();
            }
        }
    }

    public void release(){
        mShowLogThread.quit();
        ShowLogUtil.release();
    }
}
