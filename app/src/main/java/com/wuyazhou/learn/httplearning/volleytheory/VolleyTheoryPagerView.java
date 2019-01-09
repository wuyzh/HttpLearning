package com.wuyazhou.learn.httplearning.volleytheory;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.wuyazhou.learn.httplearning.R;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author 吴亚洲
 * @date 2018.7.7
 * @function
 */
public class VolleyTheoryPagerView extends FrameLayout implements View.OnClickListener {
    private Context mContext = null;
    private RelativeLayout mLayout;

    private VolleyTheoryWhileTrue mThread;

    private final PriorityBlockingQueue<String> mPriorityBlockingQueue = new PriorityBlockingQueue<String>();

    public VolleyTheoryPagerView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public VolleyTheoryPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public VolleyTheoryPagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    public void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = (RelativeLayout) inflater.inflate(R.layout.pager_volley_theory_layout, null);

        addView(mLayout);

        mThread = new VolleyTheoryWhileTrue(mPriorityBlockingQueue);

        View startThreadView = mLayout.findViewById(R.id.start_thread);
        startThreadView.setOnClickListener(this);

        View addItemView = mLayout.findViewById(R.id.add_item);
        addItemView.setOnClickListener(this);

        View stopThreadView = mLayout.findViewById(R.id.stop_thread);
        stopThreadView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_thread:
                if (!mThread.isAlive()){
                    mThread.start();
                }
                break;
            case R.id.add_item:
                mPriorityBlockingQueue.add("123");
                break;
            case R.id.stop_thread:
                mThread.quit();
                break;
            default:
                break;
        }
    }
}
