package com.io.mina;

import org.apache.commons.lang3.CharSet;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-22 14:12
 */
public class TCPClient {
    public static void main(String[] args) {
        NioSocketConnector connector=new NioSocketConnector();
        connector.getFilterChain().addLast("logger",new LoggingFilter());
        connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(
              Charset.forName("UTF-8")
        )));
        connector.setHandler(new TCPClientHandler());
        ConnectFuture connectFuture = connector.connect(new InetSocketAddress("localhost",7080));

    }

}
