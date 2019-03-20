package com.thread.Java7ConcurrencyCookbook.thread1.two;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-20 14:41
 * 在Java中，线程只能有这6种中的一种状态： new, runnable, blocked, waiting, time waiting, 或 terminated.
 */
public class Main {
    public static void main(String[] args) {
        Thread threads[]=new Thread[10];
        Thread.State status[]=new Thread.State[10];
        for (int i=0; i<10; i++) {
            threads[i]=new Thread(new Calculator(i));
            if ((i%2)==0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            }else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread "+i);
        }
        try{
            FileWriter file = new FileWriter(".\\data\\log.txt");
            PrintWriter pw = new PrintWriter(file);
            for (int i=0; i<10; i++) {
                pw.println("Main : Status of Thread "+i+" : " +threads[i].getState());
                status[i]=threads[i].getState();
            }
            for (int i=0; i<10; i++) {
                threads[i].start();
            }
            boolean finish=false;
            while (!finish) {
                for (int i=0; i<10; i++){
                    if (threads[i].getState()!=status[i]) {
                        writeThreadInfo(pw, threads[i],status[i]);
                        status[i]=threads[i].getState();
                    }
                }

                finish=true;
                for (int i=0; i<10; i++){
                    finish=finish && (threads[i].getState()==Thread.State.TERMINATED);
                }
            }

            }catch (IOException e){
            e.printStackTrace();
        }
    }
    private static void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state) {
        pw.printf("Main : Id %d - %s\n",thread.getId(),thread.getName());
        pw.printf("Main : Priority: %d\n",thread.getPriority());
        pw.printf("Main : Old State: %s\n",state);
        pw.printf("Main : New State: %s\n",thread.getState());
        pw.printf("Main : ************************************\n");
    }
    }
