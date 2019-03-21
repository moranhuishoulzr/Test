package com.thread.Java7ConcurrencyCookbook.thread2.two5;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-21 10:12
 */
public class Job implements Runnable{
    private PrintQueue printQueue;
    public Job(PrintQueue printQueue){
        this.printQueue=printQueue;
    }
    @Override
    public void run() {
        System.out.printf("%s: Going to print a document\n", Thread.
                currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has been printed\n",
                Thread.currentThread().getName());
    }
}
