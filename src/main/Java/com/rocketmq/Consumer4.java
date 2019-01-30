package com.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-01-30 11:26
 */
public class Consumer4 {
    public static void main(String[] args) throws Exception{
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("MM3");
        consumer.setNamesrvAddr("192.168.162.235:9876;192.168.162.236:9876");
        consumer.subscribe("AA","*");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg:list){
                    DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String time=ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(msg.getStoreTimestamp()), ZoneId.systemDefault()));
                    System.out.println(msg.getMsgId()+"   "+time);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        System.out.println("clientIP:"+consumer.getClientIP());
        consumer.start();
        System.out.println("Consumer Start!");
    }
}
