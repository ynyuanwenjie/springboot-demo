--远程连接mysql
mysql -u 用户名 -p密码 -h 服务器IP地址 -P 服务器端MySQL端口号 -D 数据库名
mysql -u root -p111111 -h 192.168.99.100 -P 32768 -D MetaData
set password for 'root'@'%' = password('root');


--进入客户端命令行模式,默认密码是空

:/usr/sbin>mysql -u root -p

--配置只有本机可以访问
grant all on *.* to root@localhost identified by 'root'
flush privileges;

--配置所有机器可以访问
grant all on *.* to 'root'@'%' identified by 'root';
flush privileges;

--解决乱码
set names utf8；
--版本过低会导致不能创建表不成功或者无法导入视图等问题。

--增加unique
ALTER TABLE `t_baixing_register` ADD UNIQUE `unique_index`(`mobile`, `register_status`);

--修改字段
ALTER TABLE meta_data_field_type CHANGE field_index TYPE_INDEX int;
--增加字段
alter table `user_movement_log` Add column GatewayId int not null default 0 AFTER `Regionid` ;

ALTER TABLE META_DATA_FIELD CHANGE COLUMN `TABLE_DESC` `FIELD_DESC` text CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL;

ALTER TABLE META_DATA_DATABASE ADD CONNECTION_URL varchar(500);
ALTER TABLE META_DATA_DATABASE ADD OTHER_PARAM varchar(50);
ALTER TABLE META_DATA_DATABASE ADD SERVICE_NAME varchar(50);
ALTER TABLE META_DATA_DATABASE ADD SYNC_TIME TIMESTAMP(50);

--add
ALTER TABLE meta_data_process ADD SYNC_TIME varchar(1000) AFTER PROCESS_NAME;

ALTER TABLE meta_data_process CHANGE COLUMN `SYNC_TIME` `PROCESS_DESC` varchar(1000);
ALTER TABLE META_DATA_OUTLOG ADD COLUMN `REFERENCE_DATABASE` varchar(500);

ALTER TABLE META_DATA_DATABASE ADD DATABASE_USERNAME varchar(50) AFTER CONNECTION_URL;
ALTER TABLE META_DATA_DATABASE ADD DATABASE_PASSWORD varchar(50) AFTER DATABASE_USERNAME;
ALTER TABLE META_DATA_DATABASE CHANGE COLUMN `ADATABASE_USERNAME` `DATABASE_USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL;

-------------------------------------------------------------------------------------------------------------------------------------
