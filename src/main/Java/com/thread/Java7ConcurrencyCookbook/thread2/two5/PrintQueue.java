package com.thread.Java7ConcurrencyCookbook.thread2.two5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-21 10:09
 */
public class PrintQueue {
    private final Lock queueLock=new ReentrantLock();
    public void printJob(Object document){
        queueLock.lock();
        try {
            Long duration=(long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+ ":PrintQueue: Printing a Job during "+(duration/1000)+" seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            queueLock.unlock();
        }
    }
}
