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
        producer.setNamesrvAddr("192.168.162.235:9876;192.168.162.236:9876");
        //producer.setSendMsgTimeout(2000);
        //producer.setInstanceName("pro");
        producer.start();
        for (int i=0;i<64;i++){
            Message msg=new Message("CC","tag1",("hello MQ"+i).getBytes());
            SendResult result=producer.send(msg);
            System.out.println("message："+(new String(msg.getBody())));
            System.out.println("result:"+result);
        }
        producer.shutdown();
    }
}
