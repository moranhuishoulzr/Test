package com.javamail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-03-25 16:32
 */
public class JavaMail {
    public static void main(String[] args) throws NoSuchProviderException, MessagingException,Exception {
        Properties prop =new Properties();
        prop .setProperty("mail.host", "smtp.sohu.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(prop);
        session.setDebug(true);
        Transport ts = session.getTransport();
        ts.connect("mail.qq.com","991192715","liangzuoran2862888");
        Message message = createSimpleMail(session);
        ts.sendMessage(message,message.getAllRecipients());
    }

    public static MimeMessage createSimpleMail(Session session) throws Exception{
        MimeMessage message=new MimeMessage(session);
        message.setFrom("991192715@qq.com");
        message.setRecipient(Message.RecipientType.TO,new InternetAddress("991192715@qq.com"));
        message.setSubject("只包含文本的简单邮件");
        message.setContent("你好啊","text/html;charset=UTF-8");
        return  message;
    }
}
