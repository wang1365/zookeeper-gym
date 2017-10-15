package com.xiaochuan.service;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicroService {
    private static final Logger logger = LoggerFactory.getLogger(MicroService.class);
    private String appPath;
    private String servicePath;

    @Value("${zookeeper.server}")
    private String zkServer;

    @Autowired
    @Bean
    CuratorFramework createZkClient(ApplicationArguments args) throws Exception {
        RetryPolicy policy = new RetryNTimes(3, 1000);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkServer)
                .retryPolicy(policy)
                .connectionTimeoutMs(5000)
                .build();

        client.getCuratorListenable().addListener((client1, event) -> {
            logger.info("### Watched event: {}, {}", event.getName(), event.getPath(), event.getData());
        });
        client.start();

        // 创建应用永久节点, 应用名称由命令行参数传入“--appname”
        String appName = args.getOptionValues("appname").get(0);
        this.appPath = "/" + appName;
        if (client.checkExists().forPath(appPath) == null) {
            logger.info("### Node: {} doesn't exist, create it", appPath);
            client.create().withMode(CreateMode.PERSISTENT).forPath(appPath);
        }

        // 创建服务临时节点
        this.servicePath = appPath + "/service";
        client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(servicePath);

        return client;
    }
}
