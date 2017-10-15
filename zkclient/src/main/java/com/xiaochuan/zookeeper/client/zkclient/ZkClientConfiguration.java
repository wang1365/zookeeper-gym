package com.xiaochuan.zookeeper.client.zkclient;

import com.xiaochuan.zookeeper.client.ZkClientApplication;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SuppressWarnings("unused")
@Configuration
public class ZkClientConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(ZkClientApplication.class);

    @Value("${zookeeper.server}")
    private String server;

    // TODO: when this bean is destroyed, bean's "close" is not invoked
    @Bean(destroyMethod = "close")
    CuratorFramework createZkClient() {
        logger.info("##### Start create zk client");

        // 使用curator创建zk客户端，需要指定一个重试策略
        RetryPolicy policy = new RetryNTimes(3, 1000);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(server)
                .retryPolicy(policy)
                .build();
        client.start();
        return client;
    }
}
