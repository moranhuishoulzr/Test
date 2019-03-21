package com.thread.Java7ConcurrencyCookbook.thread2.two4;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-21 10:03
 */
public class Main {
    public static void main(String[] args) {
        EventStorage storage=new EventStorage();
        Producer producer=new Producer(storage);
        Thread thread1=new Thread(producer);

        Consumer consumer=new Consumer(storage);
        Thread thread2=new Thread(consumer);

        thread2.start();
        thread1.start();


    }
}
