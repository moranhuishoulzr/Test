package com.thread.Java7ConcurrencyCookbook.thread2.two4;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-21 09:57
 */
public class EventStorage {
    private int maxSize;
    private LinkedList<Date> storage;
    public EventStorage(){
        maxSize=10;
        storage=new LinkedList<>();
    }
    public synchronized void set(){
        while (storage.size()==maxSize){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.offer(new Date());
        System.out.printf("Set: %d",storage.size());
        notifyAll();
    }

    public synchronized void get(){
        while (storage.size()==0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Get: %d: %s",storage.size(),((LinkedList<?>)storage).poll());
        notifyAll();
    }

}
