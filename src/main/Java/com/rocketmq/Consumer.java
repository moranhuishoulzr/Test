package com.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-01-18 11:08
 */
public class Consumer {
    public static void main(String[] args) throws Exception{
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("consumer1");
        consumer.setNamesrvAddr("192.168.70.128:9876;192.168.70.101:9876");
        consumer.setInstanceName("consumer");
        consumer.subscribe("topic1","*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + list);
                for (Message msg:list){
                    System.out.println("Consumer.consumeMessage:"+(new String(msg.getBody())));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        System.out.println("clientIP:"+consumer.getClientIP());
        consumer.start();
        System.out.println("Consumer Start!");
    }
}
