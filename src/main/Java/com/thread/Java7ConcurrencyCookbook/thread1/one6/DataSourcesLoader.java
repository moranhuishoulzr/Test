package com.thread.Java7ConcurrencyCookbook.thread1.one6;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 15:37
 */
public class DataSourcesLoader implements Runnable{
    @Override
    public void run() {
        System.out.println("begin DataSourcesLoader"+new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end DataSourcesLoader"+new Date());
    }
}
