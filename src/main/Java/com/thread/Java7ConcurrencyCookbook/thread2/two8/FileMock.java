package com.thread.Java7ConcurrencyCookbook.thread2.two8;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-21 11:04
 */
public class FileMock {
    private String content[];
    private int index;
    public FileMock(int size, int length){
        content=new String[size];
        for (int i=0; i<size; i++){
            StringBuilder buffer=new StringBuilder(length);
            for (int j=0; j<length; j++){
                int indice=(int)Math.random()*255;
                buffer.append((char)indice);
            }
            content[i]=buffer.toString();
        }
        index=0;
    }
}
