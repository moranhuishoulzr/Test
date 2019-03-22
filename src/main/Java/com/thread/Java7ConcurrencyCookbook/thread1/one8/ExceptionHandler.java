package com.thread.Java7ConcurrencyCookbook.thread1.one8;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 16:26
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler
{
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("An exception has been captured\n");
        System.out.printf("Thread: %s\n",t.getId());
        System.out.printf("Exception: %s: %s\n",e.getClass().getName(),e.getMessage());
        System.out.printf("Stack Trace: \n");
        e.printStackTrace(System.out); System.out.printf("Thread status: %s\n",t.getState());
    }
}