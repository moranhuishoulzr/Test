package com.io.nio.nio5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-21 13:32
 */
public class TransferFrom {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //transfrom();
        transto();
    }

    /**
     * FileChannel的transferFrom()方法可以将数据从源通道传输到FileChannel中
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void transfrom()throws IOException,FileNotFoundException{
        RandomAccessFile fromFile = new RandomAccessFile("D:\\from.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("D:\\to.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel,position, count);
    }

    public static void transto()throws IOException,FileNotFoundException{
        RandomAccessFile fromFile = new RandomAccessFile("from.txt", "rw");
        FileChannel      fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("to.txt", "rw");
        FileChannel      toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);
    }
}
