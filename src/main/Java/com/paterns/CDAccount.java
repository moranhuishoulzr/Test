package com.paterns;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-04-28 15:33
 */
public  class CDAccount extends Account{
    @Override
    protected String doCalculateAccountType() {
        return "Certificate of Deposite";
    }

    @Override
    protected double doCalculateInterestRate() {
        return 0.06;
    }
}
