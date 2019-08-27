package com.zsw.kafka.support;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author ZhangShaowei on 2019/8/21 15:21
 **/
@Slf4j
public class CustomPartitioner implements Partitioner {

    private Random random = new Random();

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //获取集群中指定topic的所有分区信息
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int numOfPartition = partitionInfos.size();
        int partitionNum = 0;
        if (key == null) {
            //key没有设置
            //随机指定分区
            partitionNum = random.nextInt(numOfPartition);
        } else {
            partitionNum = Math.abs((value.hashCode())) % numOfPartition;
        }
        log.debug("key->{},value->{}->send to partition:{}", key, value, partitionNum);
        return partitionNum;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
