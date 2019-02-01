package com.rocketmq;

import org.apache.rocketmq.client.consumer.*;
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
 * @description:单线程拉取消息，很慢很慢
 * @author: liangzr
 * @create: 2019-01-29 16:36
 */
public class Consumer_pull_thread {
    public static void process(Long offset) throws Exception{
        String groupName="BB3";
        String topicName="CC";
        final MQPullConsumerScheduleService scheduleService = new MQPullConsumerScheduleService(groupName);
        scheduleService.getDefaultMQPullConsumer().setNamesrvAddr("192.168.162.235:9876;192.168.162.236:9876");
        scheduleService.setMessageModel(MessageModel.CLUSTERING);
        scheduleService.registerPullTaskCallback(topicName, new PullTaskCallback() {
            @Override
            public void doPullTask(MessageQueue mq, PullTaskContext context) {
                MQPullConsumer consumer=context.getPullConsumer();
                try {
                    /*long offset = consumer.fetchConsumeOffset(mq, false);
                    if(offset<0){
                        offset=0;
                    }*/
                    PullResult pullResult = consumer.pull(mq, "*", offset, 32);
                    //System.out.printf("%s%n", offset + "\t" + mq + "\t" + pullResult);
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
    public static void main(String[] args) throws Exception{
        Long offset = 375L;
        process(offset);

    }
}
