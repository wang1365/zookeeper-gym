# zookeeper-learning

## Zookeeper server installation on windows
* Download zookeeper  
[http://zookeeper.apache.org/releases.html#download]()

* Set configuration
Create or open `zookeeper/config/zoo.cfg`, and set below config:
```
tickTime=2000
initLimit=10
syncLimit=5

dataDir=E:\\software\\zookeeper\\zookeeper-3.4.9\\data
dataLogDir=E:\\software\\zookeeper\\zookeeper-3.4.9\\log

clientPort=2181
```

* Start server  
`bin\zkServer.cmd`

* Verification   
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