package com.thread.Java7ConcurrencyCookbook.thread1.one11;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 18:52
 */
public class Main {
    public static void main(String[] args) {
        MyThreadGroup threadGroup=new MyThreadGroup("MyThreadGroup");
        Task task=new Task();
        for (int i=0; i<2; i++){
            Thread t=new Thread(threadGroup,task);

            t.start();
        }
    }
}
