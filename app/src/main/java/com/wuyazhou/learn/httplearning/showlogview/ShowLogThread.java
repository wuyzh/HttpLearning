package com.wuyazhou.learn.httplearning.showlogview;

import java.util.concurrent.BlockingQueue;

/**
 * @author wuyazhou
 * @function 测试while true为什么没有永远执行
 * */
public class ShowLogThread extends Thread{
    private final BlockingQueue<String> mBlockingQueue;
    private ShowLogViewContract mShowLogViewContract;
    private boolean mQuit = false;

    public ShowLogThread(BlockingQueue<String> mBlockingQueue){
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
                String string = mBlockingQueue.take();
                mShowLogViewContract.showLog("wuyazhouHttp",string);
            } catch (InterruptedException e) {
                if (mQuit) {
                    return;
                }
                continue;
            }
        }
    }
}
