package com.thread.Java7ConcurrencyCookbook.thread2.two4;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-21 10:01
 */
public class Producer implements Runnable{
    private EventStorage storage;
    public Producer(EventStorage storage){
        this.storage=storage;
    }
    @Override
    public void run() {
        for (int i=0; i<100; i++){
            storage.set();
        }
    }
}
