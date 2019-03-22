package com.thread.Java7ConcurrencyCookbook.thread1.one7;

import java.util.Date;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 16:01
 */
public class Event {
    private Date date;
    private  String event;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
