package com.thread.Java7ConcurrencyCookbook.thread1.one3;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 15:06
 *
 */
public class Main {
    public static void main(String[] args) {
        Thread task=new PrimeGenerator();
        task.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.interrupt();//中断信号

    }
}
