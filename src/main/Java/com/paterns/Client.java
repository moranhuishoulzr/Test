package com.paterns;

import java.util.RandomAccess;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-04-28 15:34
 */
public class Client {
    public static void main(String[] args) {
        Account account = new MoneyMarketAccount();

        System.out.println("货币市场账号的利息数额为：" + account.calculateInterest());
        account = new CDAccount();
        System.out.println("定期账号的利息数额为：" + account.calculateInterest());
    }
}
