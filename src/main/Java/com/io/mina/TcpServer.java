package com.io.mina;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-04-03 17:49
 */
public class TcpServer {
    public static void main(String[] args) throws Exception{
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new PrefixedStringCodecFactory(Charset.forName("UTF-8"))));
        acceptor.setHandler(new TcpServerHandle());
        acceptor.bind(new InetSocketAddress(8080));

    }

}
