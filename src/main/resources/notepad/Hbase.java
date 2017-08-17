--CAP 理论

 http://mirrors.advancedhosters.com/apache/hadoop/common/hadoop-2.6.0/hadoop-2.6.0-src.tar.gz
 --安装
 在安装hbase之前，要先安装JDK

 (单击模式，伪分布模式，全分布模式)
 --启动和停止Hbase
 ./bin/start-hbase.sh
//进入shell
 ./bin/hbase shell

 //查看服务状态
 status
 ./bin/hbase hbck --检查是否正确安装

 ./bin/stop-hbase.sh

 --创建表
 create 'test', 'cf'

 --判断表是否存在
 exists 'test'

 --判断表是否有效
 is_enabled 'test'
 is_disabled 'test'

 --显示存在的表
 list 'test'

 --显示表默认参数
 describe 'users'

 --向表中添加数据
 put 'test','row1','cf:a','value1'
 put 'test','row2','cf:b','value2'
 put 'test','row3','cf:c','value3'

 --查询表中的数据,结束不能有分号
 hbase(main):011:0> scan 'test'
ROW                           COLUMN+CELL                                                                          
 row1                         column=cf:a, timestamp=1464591685040, value=value1                                   
 row2                         column=cf:b, timestamp=1464591714260, value=value2                                   
 row3                         column=cf:c, timestamp=1464591736095, value=value3                                   
3 row(s) in 0.2930 seconds

--查询指定数据
hbase(main):014:0> get 'test','row1'
COLUMN                  CELL                                                               
 cf:a                   timestamp=1464591685040, value=value1                              
1 row(s) in 0.1920 seconds

--删除表之前需要设置表失效
disable 'test'
enable 'test'

--drop table
 drop 'test'

 --其他命令（delete ,increment）
 
--退出命令行
quit 

--修改表的值'A'-'C'
put 'mytable','four','cf:0','C'

--将整个表清空
truncate 'users'

--逻辑数据模型和物理数据模型

--Hbase字符类型
String：table,column_family
byte[]:row,cell(单元),column_qualifier
Long:timestamp;

--Hbase特点
半结构数据:逻辑模型，数据的构成是松耦合的，有利于物理分散存放。
可扩展性:
不能实施关系约束
不支持多行事务。

----------port------------------------------
hadoop master      web端口  50070   
hadoop slave       web端口  50075   

hbase master       web端口  60010   通讯端口 60000 
hbase regionserver web端口  60030   通讯端口 60020 
hbase rest         web端口  8085    通讯端口 8080        
hbase thrift       web端口  9095    通讯端口 9090 
zookeeper          通讯端口 2181 
+++++++++++++++++regionserver  start+++++++++
./bin/local-regionservers.sh start 2 3
./bin/local-regionservers.sh stop 3


+++++++++++++++++shell script++++++++
>create 'twits',{NAME => 't' ,VERSIONS => 1}
>exec $HBASE_HOME/bin/hbase shell<<EOF
>scan '-ROOT-'

>create 't','cf'
>(t = create 't', 'cf' or >t = get_table 't')
>t.put 'r2','cf:q','v_value2'
>t.get 'r2','cf:q'
>t.scan
>t.enable
>t.flush
>t.disable
>t.drop

Some examples:
>scan 'hbase:meta'
>scan 'hbase:meta', {COLUMNS => 'info:regioninfo'}
>scan 't', {COLUMNS => ['cf:1', 'cf:q'], LIMIT => 10, STARTROW => 'xyz'}
>scan 't', {COLUMNS => 'cf:q', TIMERANGE => [1303668804, 1303668904]}
>scan 't', {REVERSED => true}
>scan 't',{COLUMNS => ['cf:q','cf:1'],TIMERANGE => [1466039679917,1466039982189,1466039934500]}

--alter
hbase> alter 't', NAME => 'cf', VERSIONS => 5
hbase> alter 't1', 'f1', {NAME => 'f2', IN_MEMORY => true}, {NAME => 'f3', VERSIONS => 5}
hbase> alter 'ns1:t1', NAME => 'f1', METHOD => 'delete'
hbase> alter 'ns1:t1', 'delete' => 'f1'
hbase> alter 't1', MAX_FILESIZE => '134217728'

--count
>count 't1'
>count 't1', INTERVAL => 100000
>count 't1', CACHE => 1000
>count 't1', INTERVAL => 10, CACHE => 1000

--delete
>delete 'ns1:t1', 'r1', 'c1', ts1
>delete 't1', 'r1', 'c1', ts1
>delete 't1', 'r1', 'c1', ts1, {VISIBILITY=>'PRIVATE|SECRET'}
>delete 't','r','cf:q'

/app/hadoop/tmp
--configuration
<configuration>
  <property>
    <name>hbase.rootdir</name>
    <value>file:///home/hduser/hbase</value>
  </property>
  <property>
    <name>hbase.zookeeper.property.dataDir</name>
    <value>/home/hduser/zookeeper</value>
  </property>
</configuration>

+++++++++++++++++++++++++++
--SPOF:single point of failure(high availability cluster)
--OSDI:Operating Systems Design and Implementation 
