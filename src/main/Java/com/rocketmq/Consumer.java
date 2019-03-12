package com.rocketmq;

import org.apache.rocketmq.client.consumer.AllocateMessageQueueStrategy;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueByConfig;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueByMachineRoom;
import org.apache.rocketmq.client.consumer.store.OffsetStore;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-01-18 11:08
 */
public class Consumer {
    public static void main(String[] args) throws Exception{
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("MC");
        consumer.setNamesrvAddr("192.168.162.235:9876;192.168.162.236:9876");
      //consumer.setInstanceName("consumer");
        consumer.subscribe("AA","*");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
        consumer.setConsumeTimestamp("2019-01-30 00:00:00");
        String clientIp = consumer.getClientIP();
        OffsetStore offsetStore = consumer.getOffsetStore();
        AllocateMessageQueueByConfig config=new AllocateMessageQueueByConfig();
        consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragely());
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
