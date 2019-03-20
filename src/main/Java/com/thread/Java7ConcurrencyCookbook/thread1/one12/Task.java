package com.thread.Java7ConcurrencyCookbook.thread1.one12;

import java.util.concurrent.TimeUnit;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 19:02
 */
public class Task implements Runnable{
    @Override
    public void run() {
        try { TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
