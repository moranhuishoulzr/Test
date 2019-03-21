package com.thread.Java7ConcurrencyCookbook.thread2.two6;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-21 10:22
 */
public class Reader implements Runnable{
    private PricesInfo pricesInfo;
    public Reader (PricesInfo pricesInfo){
        this.pricesInfo=pricesInfo;
    }
    @Override
    public void run() {
        for (int i=0; i<10; i++){
            System.out.printf("%s: Price 1: %f\n", Thread.currentThread().getName(),pricesInfo.getPrice1());
            System.out.printf("%s: Price 2: %f\n", Thread.currentThread().getName(),pricesInfo.getPrice2());
        }
    }


}
