package com.paterns;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-04-28 15:33
 */
public class MoneyMarketAccount extends Account {
    @Override
    protected String doCalculateAccountType() {

        return "Money Market";
    }

    @Override
    protected double doCalculateInterestRate() {

        return 0.045;
    }
}
