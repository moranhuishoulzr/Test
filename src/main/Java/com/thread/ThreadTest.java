package com.thread;

import io.openmessaging.consumer.PushConsumer;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-05 10:15
 */
public class ThreadTest {
    public static void main(String[] args) {


    }
}
class StopRunnable implements Runnable{
    int account01 = 10;
    int account02=0;
    Object object=new Object();
    @Override
    public void run() {
        while (true){
            synchronized (object){
                account01++;
                sleep(2000);
                account02--;
                System.out.println("account01: " + account01 + "\naccount02: " + account02);
            }
        }
    }
    private void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

