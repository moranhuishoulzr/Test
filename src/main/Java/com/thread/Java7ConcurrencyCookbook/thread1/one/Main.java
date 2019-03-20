package com.thread.Java7ConcurrencyCookbook.thread1.one;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 14:35
 * 如果某个线程调用System.exit()指示终结程序，那么全部的线程都会结束执行。
 */
public class Main {
    public static void main(String[] args) {
        for (int i=1; i<=10; i++) {
            Calculator calculator=new Calculator(i);
            Thread thread=new Thread(calculator);
            thread.start();

        }
        }
}
