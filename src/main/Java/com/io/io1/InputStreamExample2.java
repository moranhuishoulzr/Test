package com.io.io1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-12 14:32
 */
public class InputStreamExample2 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream inputstream = new FileInputStream("D://out.txt");
        byte[] data = new byte[4];
        int bytesRead = inputstream.read(data);
        while(bytesRead != -1) {
            System.out.println(new String(data));
            bytesRead = inputstream.read(data,bytesRead,4);
        }
        inputstream.close();
    }
}
