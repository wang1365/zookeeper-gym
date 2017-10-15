# -*- coding: utf8 -*-
'''
使用该脚本启动zookeeper server
'''

import os
import subprocess

ZK_HOME = r'E:\software\zookeeper\zookeeper-3.4.9'

zk_server_cmd = os.path.join(ZK_HOME, r'bin\zkServer.cmd')
print(zk_server_cmd)

p = subprocess.Popen([zk_server_cmd])


# E:\software\zookeeper\zookeeper-3.4.9\bin\zkServer.cmd
