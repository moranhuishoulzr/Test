package com.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-01-18 10:54
 */
public class Producer {
    public static void main(String[] args) throws  Exception{
        DefaultMQProducer producer=new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("192.168.70.128:9876;192.168.70.101:9876");
        producer.setInstanceName("pro");
        producer.start();
        for (int i=0;i<10;i++){
            Message msg=new Message("topic1","tag1",("hello MQ"+i).getBytes());
            SendResult result=producer.send(msg);
            System.out.println("message："+(new String(msg.getBody())));
            System.out.println("result:"+result);
        }
    }
}
