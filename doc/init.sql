-- 菜单数据
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (999,'预报数据深化应用','ybsjshyy');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (1,'服务目录','fwml');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (1,'指标管控','zbgk');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (1,'模型管理','mxgl');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (1,'监控控制台','jkkzt');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (1,'查询模块管理','cxmk');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (1,'系统管理','xtgl');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (2,'服务目录管理','fwmlgl');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (2,'服务目录设置','fwmlsz');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (3,'指标管理','zbgl');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (3,'数据API管理','sjapi');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (3,'接口探测','jktc');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (3,'指标校验规则管理','zbjygzgl');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (4,'模型管理','mxgl');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (4,'模型申请','mxsq');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (4,'模型审核','mxsh');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (4,'模型任务','mxrw');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (4,'模型结果','mxjg');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (5,'监控台展板','jkzb');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (5,'监控信息汇总','jkxxhzmk');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (5,'后台监控采集','htjkcjmk');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (6,'服务及指标API查询','fwjzbapicx');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (6,'任务日志查询','rwrzcx');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (6,'监测预警信息查询','jcyjxxcx');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (7,'用户管理','yhgl');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (7,'角色管理','jsgl');
INSERT INTO DFMANAGER.T_MENU (PID,NAME,URL) VALUES (7,'用户系统管理','yhxtgl');

-- 角色
INSERT INTO DFMANAGER.T_ROLE (ROLE_NAME,ROLE_DESC,CREATE_TIME,ROLE_STATUS) VALUES ('超级管理员','初始化角色，具有最高权限',SYSDATE,'Y');

-- 角色菜单
INSERT INTO DFMANAGER.T_ROLE_MENU (ROLE_ID,MENU_ID) VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),
(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27);

-- 用户
INSERT INTO DFMANAGER.T_USER(USER_NAME,PASSWORD,REAL_NAME,POLICE_NO,ID_CARD,GENDER,USER_STATUS,CREATE_TIME)
VALUES('admin','q01HL8KooZakc2OXRdwOhQ','管理员','00000001','00000001','M','Y',SYSDATE);

-- 用户角色
INSERT INTO DFMANAGER.T_USER_ROLE(USER_ID,ROLE_ID) VALUES (1,1);

-- 服务目录菜单
INSERT INTO DFMANAGER.T_SERVICE_MENU (PID,NAME) VALUES (999,'预报数据深化应用');

-- 监控任务表
INSERT INTO T_MONITOR_TASK (TASK_CODE,TASK_DESC,MONITOR_STATUS,SORT) VALUES ('redis','Redis',0,0);
INSERT INTO T_MONITOR_TASK (TASK_CODE,TASK_DESC,MONITOR_STATUS,SORT) VALUES ('dm','达梦数据库',0,1);
INSERT INTO T_MONITOR_TASK (TASK_CODE,TASK_DESC,MONITOR_STATUS,SORT) VALUES ('hybase','海贝',0,2);
INSERT INTO T_MONITOR_TASK (TASK_CODE,TASK_DESC,MONITOR_STATUS,SORT) VALUES ('kafka','Kafka',0,3);
INSERT INTO T_MONITOR_TASK (TASK_CODE,TASK_DESC,MONITOR_STATUS,SORT) VALUES ('rabbitmq','RabbitMQ',0,4);
INSERT INTO T_MONITOR_TASK (TASK_CODE,TASK_DESC,MONITOR_STATUS,SORT) VALUES ('dfservice','dfService',0,5);
INSERT INTO T_MONITOR_TASK (TASK_CODE,TASK_DESC,MONITOR_STATUS,SORT) VALUES ('dfdispatcher','dfDispatcher',0,6);
INSERT INTO T_MONITOR_TASK (TASK_CODE,TASK_DESC,MONITOR_STATUS,SORT) VALUES ('dftasklogservice','dfTaskLogService',0,7);
INSERT INTO T_MONITOR_TASK (TASK_CODE,TASK_DESC,MONITOR_STATUS,SORT) VALUES ('dfmodelservice','dfModelService',0,8);