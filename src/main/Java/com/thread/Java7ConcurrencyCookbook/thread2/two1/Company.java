package com.thread.Java7ConcurrencyCookbook.thread2.two1;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 19:24
 */
public class Company implements Runnable{
    private Account account;
    public Company(Account account) {
        this.account=account;
    }
    @Override
    public void run() {
        for (int i=0; i<100; i++){
            account.addAmount(1000);
        }
    }
}
