package com.xiaochuan.zookeeper.client;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZkClientApplication {
    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(ZkClientApplication.class);

        RetryPolicy policy = new RetryNTimes(3, 1000);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", policy);
        client.start();
        byte[] data = client.getData().forPath("/client1");
        logger.info("Zookeeper node data: {}", new String(data));
        client.close();

        SpringApplication.run(ZkClientApplication.class, args);
        logger.info("######## zk client close.");
    }

}
