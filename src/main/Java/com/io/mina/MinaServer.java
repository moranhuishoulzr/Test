package com.io.mina;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-22 11:02
 */
public class MinaServer {
    private static int BUFFSIZE = 1024;
    private static int PORT=7080;
    private static IoAcceptor acceptor=null;
    public static void main(String[] args) throws IOException {
        acceptor=new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                LineDelimiter.WINDOWS.getValue(),
                LineDelimiter.WINDOWS.getValue()
                )));
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);//设置过期时间
        acceptor.getSessionConfig().setReadBufferSize(BUFFSIZE);
        acceptor.setHandler(new MinaHandler());
        acceptor.bind(new InetSocketAddress(PORT));


        InetSocketAddress inetSocketAddress1=new InetSocketAddress(7081);
        acceptor.bind(inetSocketAddress1);



    }
}




