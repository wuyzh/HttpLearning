package com.wuyazhou.learn.httplearning.showlogview;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author wuyzh
 * 存储需要展示的信息
 * */
public class ShowLogUtil {
    private static PriorityBlockingQueue<String> mPriorityBlockingQueue = null;

    public static void addLog(String string){
        mPriorityBlockingQueue.add(string);
    }

    public static PriorityBlockingQueue getLogQueue(){
        if (mPriorityBlockingQueue == null){
            mPriorityBlockingQueue = new PriorityBlockingQueue<String>();
        }
        return mPriorityBlockingQueue;
    }

    public static void release(){
        mPriorityBlockingQueue = null;
    }
}
