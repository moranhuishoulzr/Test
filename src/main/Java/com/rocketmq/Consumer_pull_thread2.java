package com.rocketmq;

import org.apache.rocketmq.client.consumer.*;
import org.apache.rocketmq.common.ServiceState;
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
 * @description:
 * @author: liangzr
 * @create: 2019-01-29 16:36
 */
public class Consumer_pull_thread2 {
    private static Map<MessageQueue,Long> offsetTable=new HashMap<MessageQueue, Long>();//本地记录队列的消费位置
    public static void main(String[] args) throws Exception{
        Long offset = 1300L;
        process(offset);
    }

    /**
     *
     * @param offset  设置队列消费的起始位置
     * @throws Exception
     */
    public static void process(Long offset) throws Exception{
        String groupName="KK";
        String topicName="ee1";
        final MQPullConsumerScheduleService scheduleService = new MQPullConsumerScheduleService(groupName);
        DefaultMQPullConsumer consumer=scheduleService.getDefaultMQPullConsumer();
        consumer.setNamesrvAddr("192.168.162.235:9876;192.168.162.236:9876");

        ServiceState serviceState=consumer.getDefaultMQPullConsumerImpl().getServiceState();
        if(serviceState==ServiceState.RUNNING){
            Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues(topicName);
            for(MessageQueue mq:mqs){
                putMessageQueueOffset(mq,consumer.maxOffset(mq));
            }
        }

        scheduleService.registerPullTaskCallback(topicName, new PullTaskCallback() {
            @Override
            public void doPullTask(MessageQueue mq, PullTaskContext context) {
                // System.out.println(mq.getBrokerName()+" "+mq.getQueueId());
                DefaultMQPullConsumer consumer=(DefaultMQPullConsumer)context.getPullConsumer();
                try {
                    long consumerOffset = consumer.fetchConsumeOffset(mq,true);//获取队列的消费进度
                    //long minoffset = consumer.minOffset(mq);//获取队列消息开始的offset
                    long maxoffset = consumer.maxOffset(mq);//获取队列中最大的消息位置=队列中消息的个数
                    /*if(offset<minoffset){
                        System.out.println("设置的offset不能小于minoffset会报出异常");
                    }
                    if(offset>maxoffset){
                        System.out.println("设置的offset大于maxoffset.会导致OFFSET_ILLEGAL异常，从队列开始进行消费");
                    }*/
                    PullResult pullResult = consumer.pull(mq, "*", getMessageQueueOffset(mq,consumerOffset),32);//从队列消费的位置开始消费，即消费未被消费的消息
                    putMessageQueueOffset(mq, pullResult.getNextBeginOffset());
                    PullStatus pullStatus=pullResult.getPullStatus();
                    switch (pullStatus) {
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
                            System.out.println("NO_MATCHED_MSG");
                            break;
                        case NO_NEW_MSG:
                            //System.out.println("NO_NEW_MSG");
                            break;
                        case OFFSET_ILLEGAL:
                            System.out.println("OFFSET_ILLEGAL");
                            break;
                        default:
                            break;
                    }
                    //把消费进度更新到broker端
                    consumer.updateConsumeOffset(mq, pullResult.getNextBeginOffset());
                    context.setPullNextDelayTimeMillis(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        scheduleService.start();
    }

    //本地保存队列的消费位置
    private static void putMessageQueueOffset(MessageQueue mq, long offset) {
        offsetTable.put(mq, offset);
    }

    /**
     *
     * @param mq队列
     * @param startPosition 当是一个新的队列时，设定从这个队列的哪里开始消费
     * @return
     */
    private static long getMessageQueueOffset(MessageQueue mq,Long startPosition) {
        Long offset = offsetTable.get(mq);
        if (offset != null)
            return offset;
        else
            return startPosition;
    }
}
