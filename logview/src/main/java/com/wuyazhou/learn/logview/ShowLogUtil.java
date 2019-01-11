package com.wuyazhou.learn.logview;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author wuyzh
 * 存储需要展示的信息
 * */
public class ShowLogUtil {
    public static final String CLEAN_LOG = "clean_all_clean_all";
    private static PriorityBlockingQueue<ShowLogView.LogModel> mPriorityBlockingQueue = null;
    public static final String INTERVAL= ": ";

    public static void addLog(String key,String value){
        mPriorityBlockingQueue.add(new ShowLogView.LogModel(key+INTERVAL,value));
    }

    public static PriorityBlockingQueue getLogQueue(){
        if (mPriorityBlockingQueue == null){
            mPriorityBlockingQueue = new PriorityBlockingQueue<ShowLogView.LogModel>();
        }
        return mPriorityBlockingQueue;
    }

    public static void release(){
        mPriorityBlockingQueue = null;
    }
}
