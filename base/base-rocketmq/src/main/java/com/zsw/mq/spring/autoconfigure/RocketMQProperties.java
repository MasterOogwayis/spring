/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zsw.mq.spring.autoconfigure;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.common.MixAll;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = RocketMQProperties.ROCKET_MQ_NAMESPACE)
public class RocketMQProperties {

    public static final String PROFILE_ALIYUN = "aliyun";

    public static final String PROFILE_DEFAULT = "default";

    public static final String ROCKET_MQ_NAMESPACE = "rocketmq";

    /**
     * The name server for rocketMQ, formats: `host:port;host:port`.
     */
    private String nameServer;

    /**
     * Enum type for accesChannel, values: LOCAL, CLOUD
     */
    private String accessChannel;

    private Producer producer = new Producer();

    /**
     * Configure enable listener or not.
     * In some particular cases, if you don't want the the listener is enabled when container startup,
     * the configuration pattern is like this :
     * rocketmq.consumer.listeners.<group-name>.<topic-name>.enabled=<boolean value, true or false>
     * <p>
     * the listener is enabled by default.
     */
    private Consumer consumer = new Consumer();


    @Getter
    @Setter
    public static class Producer {

        /**
         * Group name of producer.
         */
        private String group;

        /**
         * Millis of send message timeout.
         */
        private int sendMessageTimeout = 3000;

        /**
         * Compress message body threshold, namely, message body larger than 4k will be compressed on default.
         */
        private int compressMessageBodyThreshold = 1024 * 4;

        /**
         * Maximum number of retry to perform internally before claiming sending failure in synchronous mode.
         * This may potentially cause message duplication which is up to application developers to resolve.
         */
        private int retryTimesWhenSendFailed = 2;

        /**
         * <p> Maximum number of retry to perform internally before claiming sending failure in asynchronous mode. </p>
         * This may potentially cause message duplication which is up to application developers to resolve.
         */
        private int retryTimesWhenSendAsyncFailed = 2;

        /**
         * Indicate whether to retry another broker on sending failure internally.
         */
        private boolean retryNextServer = false;

        /**
         * Maximum allowed message size in bytes.
         */
        private int maxMessageSize = 1024 * 1024 * 4;

        /**
         * The property of "access-key".
         */
        private String accessKey;

        /**
         * The property of "secret-key".
         */
        private String secretKey;

        /**
         * Switch flag instance for message trace.
         */
        private boolean enableMsgTrace = true;

        /**
         * The name value of message trace topic.If you don't config,you can use the default trace topic name.
         */
        private String customizedTraceTopic = MixAll.DEFAULT_TRACE_REGION_ID;

    }

    /**
     * 这里面的配置用于兜底，若注解没有配置属性也不至于报错
     */
    @Getter
    @Setter
    public static class Consumer {

        /**
         * default
         */
        private String group;

        /**
         * default
         */
        private String topic;

        /**
         * tags || expression
         */
        private String expression = "*";

        /**
         * The property of "access-key".
         */
        private String accessKey;

        /**
         * The property of "secret-key".
         */
        private String secretKey;

        /**
         * listener configuration container
         * the pattern is like this:
         * group1.topic1 = false
         * group2.topic2 = true
         * group3.topic3 = false
         */
        private Map<String, Map<String, Boolean>> listeners = new HashMap<>();

        public Map<String, Map<String, Boolean>> getListeners() {
            return listeners;
        }

        public void setListeners(Map<String, Map<String, Boolean>> listeners) {
            this.listeners = listeners;
        }
    }

}
