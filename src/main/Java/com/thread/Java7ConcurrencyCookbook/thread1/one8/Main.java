package com.thread.Java7ConcurrencyCookbook.thread1.one8;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 16:27
 */
public class Main {
    public static void main(String[] args) {
        Task task=new Task();
        Thread thread=new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());//设置非检查异常捕获类
        thread.start();
        //Thread.setDefaultUncaughtExceptionHandler();//设置所有线程的异常捕获类

    }
}
