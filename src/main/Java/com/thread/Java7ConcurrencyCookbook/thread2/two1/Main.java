package com.thread.Java7ConcurrencyCookbook.thread2.two1;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 19:25
 */
public class Main {
    public static void main(String[] args) {
        Account account=new Account();
        account.setBalance(1000);
        Company company=new Company(account);
        Thread companyThread=new Thread(company);
        Bank bank=new Bank(account);
        Thread bankThread=new Thread(bank);
        System.out.printf("Account : Initial Balance: %f\n",account.getBalance());
        companyThread.start();
        bankThread.start();
        try {
            companyThread.join();
            bankThread.join();
            System.out.printf("Account : Final Balance: %f\n",account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
