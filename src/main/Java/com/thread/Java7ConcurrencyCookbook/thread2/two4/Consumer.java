package com.thread.Java7ConcurrencyCookbook.thread2.two4;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-21 10:02
 */
public class Consumer implements Runnable{
    private EventStorage storage;
    public Consumer(EventStorage storage){
        this.storage=storage;
    }
    @Override
    public void run() {
        for (int i=0; i<100; i++){
            storage.get();
        }
    }
}
