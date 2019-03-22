package com.io.mina;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.FilterEvent;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-22 14:18
 */
public class TCPClientHandler implements IoHandler {
    @Override
    public void sessionCreated(IoSession ioSession) throws Exception {
        System.out.println("client session  Created");
    }

    @Override
    public void sessionOpened(IoSession ioSession) throws Exception {
        ioSession.write("client session open");
    }

    @Override
    public void sessionClosed(IoSession ioSession) throws Exception {

    }

    @Override
    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {

    }

    @Override
    public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception {

    }

    @Override
    public void messageReceived(IoSession ioSession, Object message) throws Exception {
        System.out.println("收到："+message.toString());
        ioSession.write("Received ："+message.toString());

    }

    @Override
    public void messageSent(IoSession ioSession, Object o) throws Exception {

    }

    @Override
    public void inputClosed(IoSession ioSession) throws Exception {

    }

    @Override
    public void event(IoSession ioSession, FilterEvent filterEvent) throws Exception {

    }
}
