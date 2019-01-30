package com.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;

/**
 * @program: com
 * @description:
 * @author: liangzr
 * @create: 2019-01-29 16:36
 */
public class Consumer_pull {
    public static void main(String[] args) throws  Exception {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("HKOMS_DEV_CG1");
        consumer.setNamesrvAddr("192.168.162.235:9876;192.168.162.236:9876");
        consumer.start();
      //  Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("FIXUAT");

    }
}
