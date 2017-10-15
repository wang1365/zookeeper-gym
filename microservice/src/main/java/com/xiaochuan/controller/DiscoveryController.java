package com.xiaochuan.controller;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;


@RestController
public class DiscoveryController {


    @Autowired
    CuratorFramework client;

    @RequestMapping("/services")
    Collection<String> getServices() throws Exception {
        ServiceDiscovery<String> discovery = ServiceDiscoveryBuilder.builder(String.class)
                .client(client)
                .basePath("/")
                .build();
        discovery.start();
        return discovery.queryForNames();
    }
}
