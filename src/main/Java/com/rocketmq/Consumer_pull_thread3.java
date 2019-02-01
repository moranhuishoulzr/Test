package com.rocketmq;

import com.rocketmq.Exception.OffsetIllgalOffsetException;
import org.apache.rocketmq.client.consumer.*;
import org.apache.rocketmq.client.impl.MQAdminImpl;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: com
 * @description:单线程拉取消息，很慢很慢
 * @author: liangzr
 * @create: 2019-01-29 16:36
 */
public class Consumer_pull_thread3 {
    private static Map<MessageQueue,Long> offsetTable=new HashMap<MessageQueue, Long>();
    public static void main(String[] args) throws Exception{
        Long offset = 1300L;
        process(offset);
    }
    public static void process(Long offset) throws Exception{
        String groupName="NN5";
        String topicName="CC";
        final MQPullConsumerScheduleService scheduleService = new MQPullConsumerScheduleService(groupName);

        DefaultMQPullConsumer consumer=scheduleService.getDefaultMQPullConsumer();

        consumer.setNamesrvAddr("192.168.162.235:9876;192.168.162.236:9876");
        scheduleService.registerPullTaskCallback(topicName, new PullTaskCallback() {
            @Override
            public void doPullTask(MessageQueue mq, PullTaskContext context) {

                // System.out.println(mq.getBrokerName()+" "+mq.getQueueId());
                DefaultMQPullConsumer consumer=(DefaultMQPullConsumer)context.getPullConsumer();
                try {
                    long consumerOffset = consumer.fetchConsumeOffset(mq,true);//获取队列的消费进度
                    long minoffset = consumer.minOffset(mq);//获取队列消息开始的offset
                    long maxoffset = consumer.maxOffset(mq);//获取队列中最大的消息位置=队列中消息的个数
                    if(offset<minoffset){
                        System.out.println("设置的offset不能小于minoffset会报出异常");
                    }
                    if(offset>maxoffset){
                        System.out.println("设置的offset大于maxoffset.会导致OFFSET_ILLEGAL异常，从队列开始进行消费");
                    }

                    PullResult pullResult = consumer.pull(mq, "*", getMessageQueueOffset(mq,consumerOffset), 32);
                    putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                    switch (pullResult.getPullStatus()) {
                        case FOUND:
                            List<MessageExt> messageExtList = pullResult.getMsgFoundList();
                            //System.out.println("一次拉取个数"+messageExtList.size());
                            for (MessageExt m : messageExtList) {
                                DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                String time=ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(m.getStoreTimestamp()), ZoneId.systemDefault()));
                                System.out.println(m.getMsgId()+"    "+time);
                            }
                            break;
                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                            break;
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                    consumer.updateConsumeOffset(mq, pullResult.getNextBeginOffset());
                    context.setPullNextDelayTimeMillis(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        scheduleService.start();
    }

    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offsetTable.put(mq, offset);
    }

    private static long getMessageQueueOffset(MessageQueue mq,Long startPosition) {
        Long offset = offsetTable.get(mq);
        if (offset != null)
            return offset;
        else
            return startPosition;
    }
}
