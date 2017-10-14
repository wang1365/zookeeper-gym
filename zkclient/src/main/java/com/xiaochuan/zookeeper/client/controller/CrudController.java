package com.xiaochuan.zookeeper.client.controller;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
public class CrudController {
    @Autowired
    CuratorFramework client;

    @RequestMapping("/node/add")
    String create(@RequestParam("name") String name,
                  @RequestParam("data") String data) throws Exception {
        return client.create().forPath(name, data.getBytes());
    }
}
