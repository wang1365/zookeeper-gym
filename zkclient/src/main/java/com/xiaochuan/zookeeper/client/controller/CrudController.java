package com.xiaochuan.zookeeper.client.controller;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("unused")
@RestController
public class CrudController {
    @Autowired
    CuratorFramework client;

    @RequestMapping("/node/getdata")
    Map<String, Object> getZNodeData(@RequestParam("path") String path) throws Exception {
        Map<String, Object> m = new HashMap<>();
        // 获取指定节点数据
        m.put("result", new String(client.getData().forPath(path)));
        m.put("status", client.checkExists().forPath(path));

        return m;
    }

    @RequestMapping("/node/setdata")
    Map<String, Object> getZNodeData(@RequestParam("path") String path,
                                   @RequestParam("data") String data) throws Exception {
        Map<String, Object> m = new HashMap<>();
        // 设置指定节点数据
        m.put("result", client.setData().forPath(path, data.getBytes()));
        m.put("status", client.checkExists().forPath(path));

        return m;
    }

    @RequestMapping("/node/add")
    Map<String, Object> createZNode(@RequestParam("path") String path,
                                  @RequestParam("data") String data) throws Exception {
        Map<String, Object> m = new HashMap<>();
        // 创建znode节点
        m.put("result", client.create().forPath(path, data.getBytes()));
        m.put("status", client.checkExists().forPath(path));

        return m;
    }
}
