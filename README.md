# zookeeper-learning

## Zookeeper server 安装（windows）
* 下载  
[http://zookeeper.apache.org/releases.html#download]()

* 配置  
修改或新建 `zookeeper/config/zoo.cfg`, 配置如下:
```
tickTime=2000
initLimit=10
syncLimit=5

dataDir=E:\\software\\zookeeper\\zookeeper-3.4.9\\data
dataLogDir=E:\\software\\zookeeper\\zookeeper-3.4.9\\log

clientPort=2181
```

* 启动  
`bin\zkServer.cmd`

* 验证   
Use Zookeeper Client command to check it:  
`bin\zkCli.cmd`

## Zookeeper client library  
Recommend using [curator](http://curator.apache.org/) as zookeeper client library.
Here is some examples:
* Client creation 
https://github.com/apache/curator/blob/master/curator-examples/src/main/java/framework/CreateClientExamples.java

* znode crud and watch  
https://github.com/apache/curator/blob/master/curator-examples/src/main/java/framework/CrudExamples.java

* zookeeper trasaction  
https://github.com/apache/curator/blob/master/curator-examples/src/main/java/framework/TransactionExamples.java


## Issues
* Curator API failed
```
org.apache.zookeeper.KeeperException$UnimplementedException: KeeperErrorCode = Unimplemented for /c1
```  
It is because curator's version is higher than zk server's.
To solve it, you need to reduce your curator's version.
Detail solution:
[http://curator.apache.org/zk-compatibility.html]()


## 测试zookeeper服务注册与发现
### 打包microservice模块
* 启动多个实例
```
java -jar microservice.jar --server.port=8082 --appname=wxc  
java -jar microservice.jar --server.port=8083 --appname=wxc  
java -jar microservice.jar --server.port=8084 --appname=wxc1  
```

* 查看zookeeper节点情况
```
/wxc/service0000000000
/wxc/service0000000001
/wxc1/service000000000
```