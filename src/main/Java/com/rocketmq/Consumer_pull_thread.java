package com.rocketmq;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.impl.consumer.PullResultExt;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: com
 * @description:单线程拉取消息，很慢很慢
 * @author: liangzr
 * @create: 2019-01-29 16:36
 */
public class Consumer_pull_thread {
    private static Map<MessageQueue,Long> offsetTable=new HashMap<MessageQueue, Long>();
    public static void processMessage() throws Exception{
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("CCC1");
        consumer.setNamesrvAddr("192.168.162.235:9876;192.168.162.236:9876");
        consumer.start();
        try{
            Set<MessageQueue>  queues = consumer.fetchSubscribeMessageQueues("FIXUAT");
            for(MessageQueue queue:queues){
                SINGLE_MQ:while(true){
                    PullResultExt pullResult =(PullResultExt)consumer.pullBlockIfNotFound(queue, "*", getMessageQueueOffset(queue), 35);
                    putMessageQueueOffset(queue, pullResult.getNextBeginOffset());
                    switch (pullResult.getPullStatus()){
                        case FOUND:
                            List<MessageExt> messageExtList = pullResult.getMsgFoundList();
                            System.out.println("一次拉取个数"+messageExtList.size());
                            for (MessageExt m : messageExtList) {
                                DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                String time=ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(m.getStoreTimestamp()), ZoneId.systemDefault()));
                                System.out.println(m.getMsgId()+"    "+time);
                            }
                            break;
                        case NO_MATCHED_MSG:
                            break SINGLE_MQ;
                        case NO_NEW_MSG:
                            break SINGLE_MQ;
                        default:
                            break;
                    }
                }
            }
        }catch (MQClientException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws  Exception {
        processMessage();
    }
    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offsetTable.put(mq, offset);
    }

    private static long getMessageQueueOffset(MessageQueue mq) {
        Long offset = offsetTable.get(mq);
        if (offset != null)
            return offset;
        return 0;
    }
}
