---------------简介----------------------------------------------------
>sqlplus scott/tiger@ip:prot/orcl
>host cls
>TRUNCATE TABLE TABLENAME; 
>explain plan for select * from emp where deptno=10;
>select * from table(dbms_xplan.display);
>create index myindex on emp(deptno);
--sqlplus / as sysdba
sql>startup
#启动监听
oracle$:lsnrctl start
删除用户：
 drop user test cascade; 
修改用户密码：
    alter user username identified by "password";
授权
    grant select,update,delete(all) on tableName to user;
收回权限 
    revoke select on tablename from user;
当授权成功后，被授权的用户要访问时必须带表的所有者(方案)
    select * from scott.emp;
对权限的维护(延续授权)，当收回权限的时候，全部收回。
    grant select on emp to mm with grant option
三次输错密码将被锁定3天。
    create profile user_account limit filed_login_attempts 3 password_lock_time 2;
--连接url
jdbc:oracle:thin:metadatauser/metadatauser@10.10.11.40:1521/orcl11

--------------表查询技巧，创建新的oracle数据库----------------------------
字符：
    char (2000) 查询速度最快
    varchar2 (4000) 变长
    clob (4G)
数字：
    number 
    number(m,n) 表示总共有m位，其中有n位是小数
日期：
    date  05-05月-1990
    timestamp
二进制文件 (图片,声音) 
    blob 安全性要求高

添加表字段：
    alter table student add (classId number(5));
修改字段的长度： 
    alter table student modify (sex char(6));
删除一个字段：
    alter table student drop column xx;
修改表名：
    rename student to stu;
查询空值：
    select * from student where birthday is not null;

删除
    delete from student;

--------------------------------------------------------
疯狂添加数据
    create table users (
        userId number(10),
        username varchar(10),
        password varchar(10)
    );
    insert into users (userid,username,password) select * from users;
//1,2,4,8,16,31..................
--------------------------------------------------------------------

查询关键字，substr的用法
    select substr(user_number,0,3) ,user_number from tb_user_order where smg_type=3;
    select user_number from tb_user_order where smg_type=3 and substr(user_number,0,3)=151;
分组查询
    分组查询要将所分的组打印出来，可以分多个组：
    select avg(sal),max(sal),deptno,job from emp group by deptno,job;
笛卡尔积
   多表查询的条件是至少不能少于n(表的张数）-1 
   select t1.ename,t1.sal,t2.grade from emp t1,salgrade t2 where t1.sal between t2.losal and t2.hisal;
自链接
    同一张表的链接查询：
    select worker.ename,boss.ename from emp worker ,emp boss where worker.mgr=boss.empno;
子查询：
    select ename from emp where emp.deptno=(select deptno from emp where ename='SMITH');
all any
子查询查出来的结果也可以看作是一张表(内嵌视图)
    select t1.ename,t1.sal,t1.deptno,t2.s from emp t1,(select deptno,avg(sal) s from emp group by deptno) t2
    where t1.deptno=t2.deptno and t1.sal>t2.s order by deptno;
    注意：给列取别名时可以使用as 给表取别名时不能使用as!
oracle分页方式
    一：rownum
        select t1.* ,rownum rn from (select * from emp) t1;
        select * from (select t1.*,rownum rn from (select * from emp) t1 where rownum<=11) where rn>=5;
    二：rowID来分(效率最高)
用查询出来的结果，创建一张新表(快捷建表)
    create table mytable(id,name,sal,job,deptno) as select empno,ename,sal,job,deptno from emp;

-----------------------java链接oracle----------------------------------------------------------
public static void main(String[] args) {
		try {
			//1 加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("dd");
			//2 获取连接
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.195:1521:orcl","scott","tiger");
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from emp");
			while(rs.next()) {
				System.out.println(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
-------------------------日期处理------------------
to_date ('1990/05/05','yyyy/mm/dd')

---------------事务-----------------------
设置保存点，只是对未提交的事物有效，提交之后保存点失效。
    savepoint xx;
回滚：roolback to xx;
con.setAutoCommit(false);
...
...
...
con.commit();
设置只读事务
    set transaction read only;

-------------------sql 函数的使用--------------------------
字符函数：
    lower(char) :将字符串转化为小写格式。
    upper(char) :将字符串转换成大写格式。
    length(char):返回字符串长度。
    substr(char,m,n):取字符串字串。
    replace(char1,search_string,replace_string) 替换
    instr(char1,char2,[,n[,m]]:取字串在字符串的位置
数学函数：
    round(n,[m]) : 四舍五入。
    trunc
    floor(number) :向下取整
    ceil(number) : 向上取整。
    abs(n):绝对值
    sina().....
    nvl(a,b)
日期函数
   sysdate:系统时间 
   add_months(d,n)
系统函数：sys_context
    terminal : 当前会话客户所对应的终端的标识符
    lanuage:
    db_name:当前数据库名称
    nls_date_format:当前会话客户所对应的日期格式。
    session_user:当前会话客户所对应的数据库用户名。
    current_schema:当前会话客户所对应的默认方案名
    host:返回数据库所在主机的名称。
    select sys_context('USERENV','host') from dual;

----------------------数据库管理员-------------------------------------
dba职责：数据库表的逻辑备份与恢复---------------------------
导出：
    分为：导出表，导出方案，导出数据库，三种方式。
    导出用exp命令完成，该命令常用的选项有：
        userid:用于指定执行导出操作的用户名，口令，链接字符串
        tables：用于指定执行导出操作的表
        owner：用于指定执行导出操作的方案。
        full=y：用于指定执行导出操作的数据库
        inctype:用于指定执行导出操作的增量类型
        file :用于指定导出文件名
        rows：用于指定执行导出操作是否要导出表中的数据。
    导出表：
        exp userid=scott/tiger@orcl tables=emp,dept file=/home/ywj/0000001.dmp
    导出表的结构
        exp userid=scott/tiger@orcl tables=emp,dept file=/home/ywj/structure.dmp rows=n;
    直接导出 ，当表数据特别大的时候采用
       exp userid=scott/tiger@orcl tables=emp,dept file=/home/ywj/full.dmp direct=y; 
    导出方案：
        导出自己的方案 
        exp scott/tiger@orcl owner=scott file=/home/ywj/project.dmp
    导出数据库：
        导出所有数据库中的对象及数据，要求用户具有dba的权限或是exp_full_database的权限
        exp userid=sys/ccphl@orcl full=y inctype=complete file=/home/ywj/all_databases.dmp
导入：
    导入也分为导入表，导入方案，导入数据库三种方式
    imp 常用的选项有
    userid:用于指定执行导入操作的用户名，口令，链接字符串。
    tables:用于指定执行导入操作的表
    formuser:用于指定源用户
    touser:用于指定目标用户
    file:指定导入的文件名
    full=y :导入整个文件
    inctype:导入操作的增量类型
    rows:指定是否要导入表行(数据);
    ignore:如果表存在，则只导入数据
    导入表：
        imp userid=scott/tiger@orcl tables=emp,dept file=/home/ywj/00001.dmp;
    导入方案
        导入自身的方案：
        imp userid=scott/tiger file=/home/ywj/xxx.dmp
        导入其他方案：
        imp userid=system/manager file=/home/ywj/xxx.dmp fromuser=system touser=scott
    导入数据库：
        导入数据库时，会导入所有对象结构和数据库(instance)
        imp userid=scott/tiger full=y file=/home/ywj/xxx.dmp

---------------------使用数据泵的方式导入导出--------------------------
    步骤：conn / as sysdba
        alter user scott identified by tiger account unlock;
		select instance_name from v$instance;
        select userenv('language') from dual; //查询oracle字符集
		select table_name from dba_tables where owner='USERNAME';
        create or replace directory DATA_DIR as '/home/opt/oracle/oradata';
        grant read,write on directory DATA_DIR to KMSMSUSER;

    Table Exports/Imports:
        expdp scott/tiger@orcl tables=EMP,DEPT directory=DATA_DIR dumpfile=EMP_DEPT.dmp logfile=expdpEMP_DEPT.log
        impdp scott/tiger@orcl tables=EMP,DEPT directory=DATA_DIR dumpfile=EMP_DEPT.dmp logfile=impdpEMP_DEPT.log
    Schema Exports/Imports:
        expdp KMSMSUSER/KMSMSUSER@orcl schemas=KMSMSUSER directory=DATA_DIR dumpfile=auto_backup.dmp logfile=expdpKMSMSUSER.log		
        impdp scott/tiger@orcl schemas=SCOTT directory=DATA_DIR dumpfile=SCOTT.dmp logfile=impdpSCOTT.log
    Database Exports/Imports:
        expdp system/password@orcl full=Y directory=DATA_DIR dumpfile=DB10G.dmp logfile=expdpDB10G.log
        impdp system/password@orcl full=Y directory=DATA_DIR dumpfile=DB10G.dmp logfile=impdpDB10G.log
    INCLUDE and EXCLUDE:
        expdp scott/tiger@orcl schemas=SCOTT include=TABLE:"IN ('EMP', 'DEPT')" directory=DATA_DIR dumpfile=SCOTT.dmp logfile=expdpSCOTT.log
        expdp scott/tiger@orcl schemas=SCOTT exclude=TABLE:"= 'BONUS'" directory=DATA_DIR dumpfile=SCOTT.dmp logfile=expdpSCOTT.log

-----------------------数据字典和动态性能视图------------------------
数据字典：
    oracle中最重要的部分，提供了数据库的一些系统信息。
动态性能视图记载了列程启动后的相关信息。
user_xxx,all_xxx, dba_tables;
user_tables;
    显示当前用户所拥有的所有表，它只返回所对应方案的所有表。
    select table_name from user_tables;
all_tables;
    用于显示当前用户可以访问的所有表，不仅返回当前用户方案的所有表，还可以返回当前用户可以访问的其他表，(其他用户授权访问)
    select table_name from all_tables;
dba_tables;
    它会显示所有方案拥有的数据库表，但是查询数据库字典视图，要求用户必须是dba角色，或是有select any table系统权限。

------------用户名，权限，角色-------------
在建立用户时，oracle会把用户的信息存放在数据字典中，当给用户授予权限或是角色时，oracle会将权限和角色的信息存放在数据字典中
    通过查询dba_users 可以显示所有数据库用户的详细信息；
    通过查询数据字典视图dba_sys_privs,可以显示用户所具有的系统权限，
    通过查询数据字典视图dba_tab_privs，可以显示用户具有的对象权限
    通过数据字典dba_col_privs,可以显示用户具有的列权限。
    通过查询数据库字典视图dba_role_privs可以显示用户所具有的角色。
查询oracle中所有的角色 
    select * from dba_users;
查询数据库的表空间
    select tablespace_name from dba_tablespaces;
查询oracle中所有的系统权限，一般是dba.
    select * from system_privilege_map order by name;
查询oracle中所有的对象权限
    select distinct privilege from dba_tab_privs;
查询一个角色包含的系统权限
    select * from dba_sys_privs where grantee='connect';
查询对象权限 from  dba_tab_privs
查询某个用户具有什么样的角色
    select * from dba_role_privs where grantee="用户名";
显示当前数据库的全称
    select * from global_name;
动态性能视图：记录当前列成的活动信息，都是以v$开始
-----------管理表空间和数据文件--------------
表空间是数据库的逻辑组成部分，作用：
    控制数据库占用的磁盘空间，
    dba可以将不同数据类型部署到不同的位置，这样有利于提高I/o性能，同时有利于备份和恢复等管理操作
改变表空间状态(默认online)状态
    使表空间脱机：
        alter tablespace 表空间名 offline;
    使表空间联机：
        alter tablespace 表空间名 online;
    使表空间只读
        alter tablespace 表空间名 read only;
查询表空间包括的所有表
    select * from all_tables where tablespace='spaceName';
根据表名查询属于哪个表空间
    select tablespace_name table_name from user_tables where table_name ='xxx';
删除表空间
    drop tablespace spacename including contents and datafiles;
扩展表空间
    增加数据文件
    alert tablespace spacename and datafile 'path' size 20m
    增加数据文件的大小
    alter tablespace spacename 'path' resize 20m
    设置文件的自动增长
    alter tablespace datafile 'path' autoextend on next 100m maxsize 500m;

-------------------------维护数据的完整性，管理索引，管理权限和角色---------------------------
在oracle中，数据的完整性可以用三种方法实现：
    约束(not null,unique,primary key ,froeign key ,和check)
        sex char(4) default '男' check (sex in ('男','女'))
        表级定义，列级定义
    触发器，应用程序(过程，函数)
    管理索引
        单列索引：基于单个列创建的索引
        create index 索引名 on tableName(列名)
        复合索引
        基于两列或是多列的索引，在同一张表上可以有很多个所以，但是要求列的组合必须不同，
        create index emp_index on emp(ename,job),
        create index emp_index2 on emp(job,ename);
    创建索引原则：
        在大表上建立索引
        在where子句或是连接条件上经常应用的列上建立索引
        索引的层次不要超过四层
    角色和权限：
        角色：
            常用预定义角色：connect ,resource,dba
            connect角色:
                alter session,
                create cluster
                create database link
                create session
                create session
                create table
                create view
                create sequence
            resource角色：
                create cluster
                create indextype
                create table
                create sequence
                create type
                create procddure
                create trigger
            dba角色：
        自定义角色：
            建立角色(不验证) 角色公用
                create role roleName not identified;
            建立角色(需要验证)
                create role roleName identified by xxxxx;
        角色授权：和给用户授权没有区别，但是系统权限的unlimited tablespace和对象权限的with grant option选项是不能授予角色的
        角色分配：grant roleName to xxx

----------------------pl/sql---------------------
pl/sql :(procedural language/sql) 是oracle在标准的sql语言上的扩展，pl/sql不仅可以嵌入sql语言，还可以定义变量和常量，
允许使用条件语句和循环语句，允许使用例外处理各种错误，是很强大的数据库过程语言。
   过程，函数，触发器是由pl/sql编写，在oracle中，可以在java程序中调用
   提高运行性能，
   模块化的设计思想
   减少网络的传输量，
   提高安全性。
   缺点(移植性不好)
开发工具
    sqlplus,是一个oracle公司提供的一个工具
    pl/sql developer 开发工具，是一个独立的产品，(ide)
****************************************************************
创建一个简单的存储过程                                         *
    create (or replace) procedure name is                      *
        begin                                                  *
        insert into users values(01,'ywj','ynyuanwenjie');     *
        end;                                                   *
        /   --以/结束                                          *
查看错误信息                                                   *
    show error                                                 *
调用存储过程：                                                 *
   1.exec procedureName(arg1,arg2);                            *
****************************************************************

pl/sql分类
    块(编程)
        过程
        函数
        触发器
        包
编写规范：
    注释
    标识符的命名规范
        当定义变量时：建议用v_作为前缀
        当定义常量时：建议用c_作为前缀
        当定义例外时：建议用e_作为前缀
        当定义游标时：建议用_cursor作为后缀
块结构示意图
    pl/sql块由三个部分构成：定义部分，执行部分，例外处理部分。
        declare
        /*定义部分----定义常量，变量，游标，例外，复杂数据类型*/
        begin
        /*执行部分---要执行的pl/sql语句和sql语句*/
        exception
        /*例外处理部分--处理运行的各种错误*/
        end;
********************************************
SQL> set serverout on                      *
SQL> begin                                 *
  2  dbms_output.put_line('hello world');  *
  3  end;                                  *
  4  /                                     *
PL/SQL procedure successfully completed    *
********************************************
*************************************************************
SQL> declare                                                *
  2  v_name varchar(9);                                     *
  3  begin                                                  *
  4  select ename into v_name from emp where empno=&no;     *
  5  dbms_output.put_line('雇员名:'||v_name);               *
  6  end;                                                   *
  7  /                                                      *
雇员名:SMITH                                                *
PL/SQL procedure successfully completed                     *
说明：& 表示要输入的值                                      *
*************************************************************
SQL> declare
  2  v_ename varchar2(9);
  3  v_sal number(7,2);
  4  begin
  5  select ename,sal into v_ename,v_sal from emp where empno=8546;
  6  dbms_output.put_line('username: '||v_ename||' user-s sal '||v_sal);
  7  exception
  8  when no_data_found then
  9  dbms_output.put_line('查无此人');
 10  end;
 11  /
查无此人
PL/SQL procedure successfully completed
案例一,将emp表中名字为scott的用户的薪水改成1000
    create procedure procedure01(name varchar2,newSal number) is
    begin
    update emp set sal = newSal where ename=name;
    end;

--exec procedure01('SCOTT',1000);
如何在java程序中调用存储过程
    try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.1.195:1521:orcl", "SCOTT",
					"tiger");
			CallableStatement cs = con.prepareCall("{call procedure01(?,?)}");
			cs.setString(1, "SMITH");
			cs.setInt(2,2500);
			cs.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            if(cs!=null) cs.close();
            if(con!=null) con.close();
        }

--------------函数---------------------------------------------
案例一，输入雇员的姓名，返回雇员的年薪
create or replace function fun1(name1 varchar2) 
return number is yearSal number(7,2);
begin 
select sal*12+nvl(comm,0)*12 into yearSal from emp where ename=name1;
return yearSal;
end;
调用函数
var x number;
call fun1('KING') into:x;
包：
    包用于在逻辑上组合过程和函数，它由包规范和包体两部分组成，
    使用create package 命令来创建包，
    包的规范只包含了过程和函数的说明，但是没有过程和函数的实现代码，包体用于实现包规范中的过程和函数
    建立包体可以使用create package body 命令
创建包：
    create or replace package package01 is 
    procedure update_sal (username varchar2,newSal number);
    function annual_income(username varchar2) return number;
    end;
创建包体：
create or replace package body package01 is
procedure update_sal(username varchar2,newSal number)
is
begin
update emp set sal=newSal where ename=username;
end;
function annual_income(username varchar2)
return number is
annual_salary number;
begin
select sal*12 into annual_salary from emp where ename=username;
end;
end;
/
调用包：(带上包名)
    exec package01.update_sal('SCOTT',2200);
    
---------触发器-----------------------------------
触发器是隐含的执行的存储过程，当定义触发器是，必须要指定触发的事件和触发的操作，常用的触发事件包括，insert，update，delete语句，
而触发操作实际上就是一个pl/sql块，可以使用create trigger来建立触发器。可以维护数据的安全和一致性。
    在编写pl/sql时，可以定义变量和常量，主要有：
        标量类型(scalar)
        复合类型(composite)--用于存放多个变量
            记录
            表(相当于数组,下标可以是负数)
        参照类型(reference) 
        lob(large object)
        %type(emp.ename%type-->联动)
        参照变量 游标变量 -ref cursor 
实例：使用pl/sql编写一个块，可以输入部门号，并显示该部门所有员工的姓名和工资。
declare
--定义游标类型 sp_emp_cursor
type sp_emp_cursor is ref cursor;
--定义一个游标变量
test_cursor sp_emp_cursor;
v_ename emp.ename%type;
v_sal emp.sal%type;
begin
--把test_cursor和一个select结合
open test_cursor for select ename,sal from emp where deptno=&nn;
--循环取出
loop
fetch test_cursor into v_ename,v_sal;
exit when test_cursor%notfound;
dbms_output.put_line('名字:'||v_ename||'工资: '||v_sal);
end loop;
close test_cursor;
end;
/
输入和输出的存储过程
create or replace procedure pro02
(no in number,empName out varchar2) is
begin
select ename into empName from emp where empno=no;
end;
java调用
Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.1.195:1521:orcl", "SCOTT",
					"tiger");
			cs = con.prepareCall("{call pro02(?,?)}");
			cs.setInt(1, 7788);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
			cs.execute();
			String str = cs.getString(2);
			System.out.println(str);
这种方式只能返回一条记录，要想返回结果集，必须使用package
--1.创建一个包，定义一个test_cursor的游标
SQL> create or replace package testPackage as
  2  type test_cursor is ref cursor;
  3  end testPackage;
  4  /
Package created

SQL> 
--2.创建存储过程
SQL> create or replace procedure proc09
  2  (dep_no in number,p_cursor out testPackage.test_cursor) is
  3  begin
  4  open p_cursor for select * from emp where deptno=dep_no;
  5  end;
  6  /
Procedure created

--3.在java程序中调用
************************************************************************************
public static void main(String[] args) {                                           *
		Connection con = null;                                                     *
		CallableStatement cs = null;                                               *
		try {                                                                    
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.1.195:1521:orcl", "SCOTT",          *
					"tiger");
			cs= con.prepareCall("{call proc09(?,?)}");
			cs.setInt(1,10);                                                       *
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet)cs.getObject(2);
			while(rs.next()) {
				//System.out.println(rs.getInt(1)+" , "+rs.getString(2));          *
				System.out.println("xx");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {                                                 *
			e.printStackTrace();
		} finally {
			try {
				if(cs!=null) cs.close();                                           *
			} catch (SQLException e) {
				e.printStackTrace();
			}                                                                      *
			try {
				if(con!=null) con.close();
			} catch (SQLException e) {                                             *
				e.printStackTrace();                                               *
			}                                                                      *
		}                                                                          *
	}                                                                              *
************************************************************************************
分页：
create or replace package pagerPackage as
type pager_cursor is ref cursor;
end pagerPackage;
/
--------------------------------------------------
create or replace procedure pagerProcedure
(
tableName in varchar2,
pagerSize in number, --每页显示多少条
pagerNow in number,
myRows out number,--总共记录数
myPagerCount out number, --总的页数
pager_cursor out pagerPackage.pager_cursor
) is
v_sql varchar2(1000);
v_begin number:=(pagerNow-1)*pagerSize+1;
v_end number:=pagerNow*pagerSize;
begin
v_sql:='select * from 
    (select t1.*,rownum rn from (select * from '||tableName||') t1 
     where rownum<='||v_end||') where rn>='||v_begin;
open pager_cursor for v_sql;
--组织一个sql
v_sql:='select count(*) from '||tableName;

execute immediate v_sql into myRows;
if mod(myRows,pagerSize)=0 then
myPagerCount:=myRows/pagerSize;
else
myPagerCount:=myRows/pagerSize+1;
end if;
close pager_cursor;
end;
/
在java中调用
public static void main(String[] args) {
	Connection con = null;
	CallableStatement cs = null;
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.1.195:1521:orcl", "SCOTT",
				"tiger");
		cs = con.prepareCall("{call pagerProcedure(?,?,?,?,?,?)}");
		// 给输入参数赋值   表
		cs.setString(1, "emp");
		//总共页数
		cs.setInt(2, 16);
		//从多少页开始
		cs.setInt(3, 1);
		// 给输出参数取值
		cs.registerOutParameter(4, oracle.jdbc.OracleTypes.INTEGER);
		cs.registerOutParameter(5, oracle.jdbc.OracleTypes.INTEGER);
		cs.registerOutParameter(6, oracle.jdbc.OracleTypes.CURSOR);
		cs.execute();
		System.out.println("总记录:"+cs.getInt(4) + "条-- 共 " + cs.getInt(5)+"页");
		ResultSet rs = (ResultSet) cs.getObject(6);
		while (rs.next()) {
		System.out.println("EMPNO: "+rs.getInt(1) + " ENAME: " + rs.getString(2) + " JOB："
					+ rs.getString(3) + " MGR: " + rs.getInt(4) + " HIREDATE: "
					+ rs.getDate(5) + " SAL: " + rs.getInt(6) + " COMM："
					+ rs.getInt(7)+" DEPTNO: "+rs.getInt(8));
			//System.out.println(rs.toString());
		}

	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (cs != null)
				cs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
----------------oracle的视图---------------------------------------
视图是一个虚拟表，其内容由查询定义，同真实的表一样，视图包含一系列带有名称的列和行数据，
但是，视图并不在数据库中以储存的数据值集形式存在，行和列数据来自有定义视图的查询所应用的表，并在引用视图时动态生成。

----------c3p0 连接池------------------------------
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"
		destroy-method="close">
		<property name="driverClass" value="oracle.jdbc.driver.OracleDriver" />
		<property name="jdbcUrl" value="jdbc:oracle:thin:@192.168.1.195:1521:orcl" />
		<property name="user" value="KMSMSUSER" />
		<property name="password" value="KMSMSUSER" />
		<property name="password" value="root" />
		<!--连接池中保留的最小连接数。 -->
		<property name="minPoolSize" value="5" />

		<!--连接池中保留的最大连接数。Default: 15 -->
		<property name="maxPoolSize" value="20" />

		<!--初始化时获取的连接数，取值应在minPoolSize与maxPoolSize之间。Default: 3 -->
		<property name="initialPoolSize" value="10" />

		<!--最大空闲时间,60秒内未使用则连接被丢弃。若为0则永不丢弃。Default: 0 -->
		<property name="maxIdleTime" value="60" />

		<!--当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。Default: 3 -->
		<property name="acquireIncrement" value="5" />

		<!--JDBC的标准参数，用以控制数据源内加载的PreparedStatements数量。但由于预缓存的statements 属于单个connection而不是整个连接池。所以设置这个参数需要考虑到多方面的因素。 
			如果maxStatements与maxStatementsPerConnection均为0，则缓存被关闭。Default: 0 -->
		<property name="maxStatements" value="0" />

		<!--每60秒检查所有连接池中的空闲连接。Default: 0 -->
		<property name="idleConnectionTestPeriod" value="60" />

		<!--定义在从数据库获取新连接失败后重复尝试的次数。Default: 30 -->
		<property name="acquireRetryAttempts" value="30" />

		<!--获取连接失败将会引起所有等待连接池来获取连接的线程抛出异常。但是数据源仍有效 保留，并在下次调用getConnection()的时候继续尝试获取连接。如果设为true，那么在尝试 
			获取连接失败后该数据源将申明已断开并永久关闭。Default: false -->
		<property name="breakAfterAcquireFailure" value="true" />
	</bean>

	<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
		<property name="dataSource" ref="dataSource" />
	</bean>
	
	
----------------------创建oracleKMSMSUSER表空间-----------------------------
CREATE TABLESPACE SMSDB
DATAFILE '/home/opt/oracle/oradata/orcl/SMSDB.dbf'
size 4096M
AUTOEXTEND ON NEXT 512M 
MAXSIZE UNLIMITED
;

CREATE TEMPORARY TABLESPACE "SMSTEMP"
TEMPFILE '/home/opt/oracle/oradata/orcl/SMSTEMP.dbf'
SIZE 1024M
AUTOEXTEND ON NEXT 256M
MAXSIZE 2048M
;

chown -R oracle:oinstall /home/opt/oracle/oradata
chmod -R 775 /home/opt/oracle/oradata

alter user KMSMSUSER quota 100M on 'USERS';
grant unlimited tablespace to KMSMSUSER;

grant create sequence to KMSMSUSER;
CREATE SEQUENCE SQL_T_SMSMO
  MINVALUE 1
  MAXVALUE 999999999999999999999999999
  START WITH 1
  INCREMENT BY 1
  CACHE 20;

------------------------------------------example sql----------------------------------------
select t1.mms_subject "彩信主题", t1.passdate "发送日期", t1.pass "网关响应数量", nvl(t2.nopass,0) "网关未响应数量", nvl(round((t2.nopass/(t1.pass + t2.nopass))*100, 2), 0) "发送失败率(%)"
from 

(select t.mms_subject, to_char(t.record_date, 'yyyy-mm-dd') passdate , nvl(sum(t.send_numbers), 0) pass
  from TB_MMS_REPORT t
  where t.handle_type=1
group by t.mms_subject, to_char(t.record_date, 'yyyy-mm-dd'), t.handle_type
order by to_char(t.record_date, 'yyyy-mm-dd') ) t1
,
(select t.mms_subject, to_char(t.record_date, 'yyyy-mm-dd') nopassdate, nvl(sum(t.send_numbers), 0) nopass
  from TB_MMS_REPORT t
  where t.handle_type=0
group by t.mms_subject, to_char(t.record_date, 'yyyy-mm-dd'), t.handle_type
order by to_char(t.record_date, 'yyyy-mm-dd')) t2

where t1.mms_subject = t2.mms_subject(+)
order by t1.passdate
----------------------------------------------------------------------------------------------

