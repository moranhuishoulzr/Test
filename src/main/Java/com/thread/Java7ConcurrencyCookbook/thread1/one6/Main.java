package com.thread.Java7ConcurrencyCookbook.thread1.one6;

import java.util.Date;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 15:42
 */
public class Main {
    public static void main(String[] args) {
        DataSourcesLoader dsLoader = new DataSourcesLoader();
        Thread thread1 = new Thread(dsLoader);
        NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
        Thread thread2 = new Thread(ncLoader);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("main end "+new Date());
    }
}
