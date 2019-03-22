package com.io.mina;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.FilterEvent;

import java.util.Date;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-22 11:20
 */
public class MinaHandler implements IoHandler {
    @Override
    public void sessionCreated(IoSession ioSession) throws Exception {
        System.out.println("sessionCreated");
    }

    @Override
    public void sessionOpened(IoSession ioSession) throws Exception {
        System.out.println("sessionOpened");
    }

    @Override
    public void sessionClosed(IoSession ioSession) throws Exception {
        System.out.println("sessionClosed");
    }

    @Override
    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {
        System.out.println("sessionIdle");
    }

    @Override
    public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception {
        System.out.println("exceptionCaught");
    }

    @Override
    public void messageReceived(IoSession ioSession, Object msg) throws Exception {
        System.out.println("接收到："+(String)msg);
        //如果接收到exit就关闭连接
        if("exit".equals((String)msg)){
            ioSession.close();
        }
        else{
            Date date = new Date();
            ioSession.write("\n当前系统时间"+date+"\n");

        }

    }

    @Override
    public void messageSent(IoSession ioSession, Object o) throws Exception {
        System.out.println("messageSent");
    }

    @Override
    public void inputClosed(IoSession ioSession) throws Exception {
        System.out.println("inputClosed");
    }

    @Override
    public void event(IoSession ioSession, FilterEvent filterEvent) throws Exception {

    }
}
