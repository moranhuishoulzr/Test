package com.thread.Java7ConcurrencyCookbook.thread1.one5;

import java.util.concurrent.TimeUnit;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 15:31
 */
public class Main {
    public static void main(String[] args) {
        FileClock clock=new FileClock();
        Thread thread=new Thread(clock);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
