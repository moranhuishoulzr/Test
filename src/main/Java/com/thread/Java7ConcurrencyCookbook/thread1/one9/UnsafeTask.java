package com.thread.Java7ConcurrencyCookbook.thread1.one9;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 16:35
 */
public class UnsafeTask implements Runnable {
    private Date startDate;
    @Override
    public void run() {
        startDate=new Date();
        System.out.printf("Starting Thread: %s : %s\n",Thread. currentThread().getId(),startDate);
        try {
            TimeUnit.SECONDS.sleep( (int)Math.rint(Math.random()*10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread Finished: %s : %s\n",Thread. currentThread().getId(),startDate);
    }
}