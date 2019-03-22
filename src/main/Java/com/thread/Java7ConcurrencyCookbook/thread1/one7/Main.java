package com.thread.Java7ConcurrencyCookbook.thread1.one7;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 16:09
 */
public class Main {
    public static void main(String[] args) {
        Deque<Event> deque = new ArrayDeque<Event>();
        WriterTask writer = new WriterTask(deque);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(writer);
            thread.start();
        }
        CleanerTask cleaner = new CleanerTask(deque);
        cleaner.start();
    }
    }
