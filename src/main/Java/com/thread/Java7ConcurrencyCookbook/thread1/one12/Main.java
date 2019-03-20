package com.thread.Java7ConcurrencyCookbook.thread1.one12;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 19:02
 */
public class Main {
    public static void main(String[] args) {
        MyThreadFactory factory=new MyThreadFactory("MyThreadFactory");
        Task task=new Task();
        Thread thread;
        System.out.printf("Starting the Threads\n");
        for (int i=0; i<10; i++){
            thread=factory.newThread(task);
            thread.start();
        }
        System.out.printf("Factory stats:\n");
        System.out.printf("%s\n",factory.getStats());
    }
}
