package com.io.io1;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 read（byte []）方法将尝试将尽可能多的字节读入作为参数给出的字节数组，因为数组具有空间。
 该方法返回一个 int ，其值是实际读取了多少字节，这点和 read() 方法不一样。

 将输出 123456789678 ，而不是预期的 123456789 ！
 因为第一次读取进 bytes 是 1234 ，第二次将是 5678 ，现在只剩下 9 一个数字了，注意此时 bytes 的值是 5678 ，然后再读取剩下 1个 9，不能装满 bytes 了，只能覆盖 bytes的第一个字节，最后返回的bytes 是 9678。
 所以记住检查返回的 int 以查看实际读入字节数组的字节数。
 int read(byte[], int offset, int length);方法和 read（byte []）方法差不多，只是增加了偏移量和指定长度。
 */
public class InputStreamExample1 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream input = new ByteArrayInputStream("123456789".getBytes());
        byte[] bytes = new byte[4]; // 每次只读取 4 个字节
        int data = input.read(bytes);
        while (data != -1) {
            System.out.print(new String(bytes));
            data = input.read(bytes);
        }
    }
}
