package com.thread.Java7ConcurrencyCookbook.thread1.one9;

import java.util.concurrent.TimeUnit;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 16:36
 */
public class Main {
        public static void main(String[] args) {
           // UnsafeTask task=new UnsafeTask();
            SafeTask safeTask=new SafeTask();
            for (int i=0; i<3; i++){
                Thread thread=new Thread(safeTask);
                thread.start();
                try { TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
}

