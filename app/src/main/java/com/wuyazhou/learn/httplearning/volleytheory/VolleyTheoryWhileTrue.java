package com.wuyazhou.learn.httplearning.volleytheory;

import com.wuyazhou.learn.logview.LogShowUtil;

import java.util.concurrent.BlockingQueue;

/**
 * @author wuyazhou
 * @function 测试while true为什么没有永远执行
 * */
public class VolleyTheoryWhileTrue extends Thread{
    private final BlockingQueue<String> mBlockingQueue;
    private boolean mQuit = false;

    public VolleyTheoryWhileTrue(BlockingQueue<String> mBlockingQueue){
        this.mBlockingQueue = mBlockingQueue;
    }

    public void quit() {
        mQuit = true;
        interrupt();
    }

    @Override
    public void run() {
        while (true){
            try {
                String string = mBlockingQueue.take();
                LogShowUtil.addLog("wuyazhouHttp",string);
            } catch (InterruptedException e) {
                if (mQuit) {
                    LogShowUtil.addLog("wuyazhouHttps","线程结束");
                    return;
                }
                continue;
            }

        }
    }
}
