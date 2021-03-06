package com.thread.Java7ConcurrencyCookbook.thread1.one6;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 15:41
 */
public class NetworkConnectionsLoader implements Runnable{
    @Override
    public void run() {
        System.out.println("begin NetworkConnectionsLoader"+new Date());
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end NetworkConnectionsLoader"+new Date());
    }
}
