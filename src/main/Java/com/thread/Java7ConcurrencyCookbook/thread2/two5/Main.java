package com.thread.Java7ConcurrencyCookbook.thread2.two5;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-21 10:13
 */
public class Main {
    public static void main(String[] args) {
        PrintQueue printQueue=new PrintQueue();
        Thread thread[]=new Thread[10];
        for (int i=0; i<10; i++){
            thread[i]=new Thread(new Job(printQueue),"Thread "+ i);
        }
        for (int i=0; i<10; i++){
            thread[i].start();
        }
    }
}
