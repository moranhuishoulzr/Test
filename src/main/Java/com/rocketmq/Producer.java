package com.rocketmq;

import org.apache.log4j.Logger;
import org.apache.rocketmq.client.ClientConfig;
import org.apache.rocketmq.client.QueryResult;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.BrokerConfig;
import org.apache.rocketmq.common.message.Message;



/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-01-18 10:54
 */
public class Producer {
    private static Logger logger1=Logger.getLogger(Producer.class);
    public static void main(String[] args) throws  Exception{
        DefaultMQProducer producer=new DefaultMQProducer("FF");
        producer.setNamesrvAddr("192.168.162.235:9876;192.168.162.236:9876");
        //producer.setSendMsgTimeout(2000);
        //producer.setInstanceName("pro");
        producer.start();
       // QueryResult result=producer.queryMessage("",10,1,5);
        ClientConfig config = new ClientConfig();
        BrokerConfig brokerConfig = new BrokerConfig();
        for (int i=0;i<8;i++){
            Message msg=new Message("P1","tag",("hello MQ"+i).getBytes());
            SendResult result=producer.send(msg);
            System.out.println("message："+(new String(msg.getBody())));
            System.out.println("result:"+result);
        }
        producer.shutdown();
    }
}
