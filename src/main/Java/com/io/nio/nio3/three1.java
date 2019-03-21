package com.io.nio.nio3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-21 11:21
 * 当向buffer写入数据时，buffer会记录下写了多少数据。一旦要读取数据，需要通过flip()方法将Buffer从写模式切换到读模式。在读模式下，可以读取之前写入到buffer的所有数据。 *
 * 一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入。有两种方式能清空缓冲区：调用clear()或compact()方法。clear()方法会清空整个缓冲区。compact()方法只会清除
 * 已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。
 * 缓冲区本质上是一块可以写入数据，然后可以从中读取数据的内存。这块内存被包装成NIO Buffer对象，并提供了一组方法，用来方便的访问该块内存。
 */
public class three1 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        RandomAccessFile randomAccessFile=new RandomAccessFile("D:\\out.txt","rw");
        FileChannel fileChannel= randomAccessFile.getChannel();
        ByteBuffer byteBuffer= ByteBuffer.allocate(48);//必须要分配
        int bytesRead=fileChannel.read(byteBuffer);
        while (bytesRead !=-1){
            byteBuffer.flip();//切换成读模式
            while (byteBuffer.hasRemaining()){
                System.out.print((char)byteBuffer.get());
            }
            byteBuffer.clear();//清空缓冲区
            bytesRead=fileChannel.read(byteBuffer);
        }
        randomAccessFile.close();
    }
}
