package com.wuyazhou.learn.logview;

import java.util.concurrent.BlockingQueue;

/**
 * @author wuyazhou
 * @function 测试while true为什么没有永远执行
 * */
public class ShowLogThread extends Thread{
    private final BlockingQueue<ShowLogView.LogModel> mBlockingQueue;
    private ShowLogViewContract mShowLogViewContract;
    private boolean mQuit = false;


    public ShowLogThread(BlockingQueue<ShowLogView.LogModel> mBlockingQueue){
        this.mBlockingQueue = mBlockingQueue;
    }

    public void start(ShowLogViewContract showLogViewContract){
        super.start();
        this.mShowLogViewContract = showLogViewContract;
        mQuit = false;
    }

    public void quit() {
        mShowLogViewContract = null;
        mQuit = true;
        interrupt();
    }

    @Override
    public void run() {
        while (true){
            try {
                if (mShowLogViewContract == null){
                    return;
                }
                ShowLogView.LogModel logModel = mBlockingQueue.take();
                mShowLogViewContract.showLog(logModel.key,logModel.value);
            } catch (InterruptedException e) {
                if (mQuit) {
                    return;
                }
                continue;
            }
        }
    }
}
