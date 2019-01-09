package com.wuyazhou.learn.httplearning.volleytheory;

import android.util.Log;

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
                Log.d("wuyazhouHttp","string: "+string);
            } catch (InterruptedException e) {
                if (mQuit) {
                    Log.d("wuyazhouHttp","线程结束");
                    return;
                }
                continue;
            }

        }
    }
}
