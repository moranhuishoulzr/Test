package com.thread.Java7ConcurrencyCookbook.thread2.two3;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-21 09:41
 */
public class Cinema {
    private final Object controlCinema1;
    private final Object controlCinema2;
    private long vacanciesCinema1;
    private long vacanciesCinema2;
    public Cinema(){
        controlCinema1=new Object();
        controlCinema2=new Object();
        vacanciesCinema1=20;
        vacanciesCinema2=20;
    }
    //当第一个电影院出售一些门票将调用它。使用controlCinema1对象来控制访问synchronized的代码块。
    public boolean sellTickets1 (int number) {
        synchronized (controlCinema1) {
            if (number<vacanciesCinema1) {
                vacanciesCinema1-=number;
                return true;
            } else {
                return false;
            }
        }
    }
    //当第二个电影院出售一些门票将调用它。使用controlCinema2对象来控制访问synchronized的代码块。
    public boolean sellTickets2 (int number) {
        synchronized (controlCinema2) {
            if (number<vacanciesCinema2) {
                vacanciesCinema2-=number;
                return true;
            } else {
                return false;
            }
        }
    }
    //当第一个电影院被退回一些票时将调用它。使用controlCinema1对象来控制访问synchronized的代码块。
    public boolean returnTickets1 (int number) {
        synchronized (controlCinema1) {
            vacanciesCinema1+=number;
            return true;
        }
    }

    //当第二个电影院被退回一些票时将调用它。使用controlCinema2对象来控制访问synchronized的代码块。
    public boolean returnTickets2 (int number) {
        synchronized (controlCinema2) {
            vacanciesCinema2+=number;
            return true;
        }
    }
    public long getVacanciesCinema1() {
        return vacanciesCinema1;
    }
    public long getVacanciesCinema2() {
        return vacanciesCinema2;
    }





}
