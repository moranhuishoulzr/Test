package com.thread.Java7ConcurrencyCookbook.thread1.one11;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 18:50
 */
public class MyThreadGroup extends ThreadGroup {
    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("The thread %s has thrown an Exception\n",t.getId());
        e.printStackTrace(System.out);
        System.out.printf("Terminating the rest of the Threads\n");
        interrupt();
    }
}
