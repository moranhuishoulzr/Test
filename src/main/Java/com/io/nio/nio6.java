package com.io.nio;

import java.io.IOException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-21 13:47
 */
public class nio6 {
    public static void main(String[] args) throws IOException {
        Selector selector=Selector.open();
        DatagramChannel datagramChannel=DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        SelectionKey selectionKey=datagramChannel.register(selector,SelectionKey.OP_READ);



    }
}
