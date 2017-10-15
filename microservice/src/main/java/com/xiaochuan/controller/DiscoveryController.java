package com.xiaochuan.controller;

import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;


@RestController
public class DiscoveryController {


    @Autowired
    ServiceDiscovery<String> discovery;

    @RequestMapping("/services")
    Collection<ServiceInstance<String>> getServices(@RequestParam("service")String service) throws Exception {
        Collection<ServiceInstance<String>> instances = discovery.queryForInstances(service);
        return instances;
    }
}
