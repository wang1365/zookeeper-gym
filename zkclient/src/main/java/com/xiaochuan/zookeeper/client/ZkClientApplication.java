package com.xiaochuan.zookeeper.client;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ZkClientApplication {
    Logger logger = LoggerFactory.getLogger(ZkClientApplication.class);

    // TODO: when this bean is destroyed, bean's "close" is not invoked
    @Bean
    CuratorFramework createZkClient() {
        logger.info("##### Start create zk client");
        RetryPolicy policy = new RetryNTimes(3, 1000);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1")
                .retryPolicy(policy)
                .build();
        client.start();
        return client;
    }

    public static void main(String[] args) throws Exception {

        SpringApplication.run(ZkClientApplication.class, args);
    }

}
