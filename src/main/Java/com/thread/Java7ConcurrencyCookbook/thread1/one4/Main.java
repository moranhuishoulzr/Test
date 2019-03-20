package com.thread.Java7ConcurrencyCookbook.thread1.one4;

import java.util.concurrent.TimeUnit;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 15:17
 */
public class Main {
    public static void main(String[] args) {
        FileSearch searcher=new FileSearch("C:\\","autoexec.bat");
        Thread thread=new Thread(searcher);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

}
