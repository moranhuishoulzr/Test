package com.io.io1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *如果 read() 方法返回 -1 ，则表示已到达流的末尾，这意味着在 InputStream 中不再有要读取的数据。
 * 也就是说，-1 作为 int 值，而不是 -1 作为 char 或 short，这里有区别！
 */
public class InputStreamExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream inputStream = new FileInputStream("D:\\out.txt");
        //do something with data...
        int data = inputStream.read();
        while (data != -1) {
            System.out.print((char) data);
            data = inputStream.read();
        }
        inputStream.close();
    }
}
