package com.xiaochuan.service;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.x.discovery.*;
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

    @Value("${server.port}")
    private int port;

    @Autowired
    @Bean
    ServiceDiscovery<String> createServiceProvider(ApplicationArguments args) throws Exception {
        // 创建ZK client
        RetryPolicy policy = new RetryNTimes(3, 1000);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkServer)
                .retryPolicy(policy)
                .connectionTimeoutMs(5000)
                .build();
        client.start();

        // 启动服务发现
        ServiceDiscovery<String> serviceDiscovery = ServiceDiscoveryBuilder.builder(String.class)
                .client(client)
                .basePath("/")
                .build();
        serviceDiscovery.start();

        // 为当前webapp生成一个服务实例
        String appName = args.getOptionValues("appname").get(0);
        ServiceInstance<String> instance = ServiceInstance.<String>builder()
                .name(appName)
                .serviceType(ServiceType.DYNAMIC_SEQUENTIAL)
                .port(port)
                .build();

        // 服务注册到zk server
        serviceDiscovery.registerService(instance);

        return serviceDiscovery;
    }
}
