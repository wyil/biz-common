
-- {{ 

CREATE TABLE qm_approval_data(
    id VARCHAR(64) NOT NULL   COMMENT 'id 主键id' ,
    flow_key VARCHAR(50)    COMMENT '流程key（模块）' ,
    business_id VARCHAR(64)    COMMENT '业务id' ,
    flow_node VARCHAR(50)    COMMENT '审批节点' ,
    action VARCHAR(32)    COMMENT '审批动作 agree;refuse;' ,
    opinion VARCHAR(4000)    COMMENT '审批意见' ,
    json_data VARCHAR(4000)    COMMENT '审批数据' ,
    create_user_id VARCHAR(32)    COMMENT '创建人' ,
    create_user_name VARCHAR(32)    COMMENT '创建人姓名' ,
    create_time DATETIME    COMMENT '创建时间' ,
    update_user_id VARCHAR(32)    COMMENT '更新人' ,
    update_time DATETIME    COMMENT '更新时间' ,
    deleted VARCHAR(1)    COMMENT 'deleted 0未删除；1删除' ,
    PRIMARY KEY (id)
) COMMENT = '审批数据表 审批数据表';

 
CREATE TABLE qm_approval_log(
    id VARCHAR(64) NOT NULL   COMMENT 'id 主键id' ,
    flow_key VARCHAR(50)    COMMENT '流程（模块）' ,
    business_id VARCHAR(64)    COMMENT '业务id' ,
    opinion VARCHAR(4000)    COMMENT '审批意见' ,
    approval_result VARCHAR(50)    COMMENT '审批动作+结果' ,
    file_group_id VARCHAR(128)    COMMENT '审批附件' ,
    create_user_id VARCHAR(64)    COMMENT '创建人' ,
    create_user_name VARCHAR(128)    COMMENT '创建人姓名' ,
    create_time DATETIME    COMMENT '创建时间' ,
    PRIMARY KEY (id)
) COMMENT = '审批流水表 审批流水表';

 
CREATE TABLE qm_approval_config(
    id VARCHAR(64) NOT NULL   COMMENT 'id 主键id' ,
    flow_key VARCHAR(50)    COMMENT '流程key（模块）' ,
    approval_node VARCHAR(50)    COMMENT '审批节点' ,
    node_desc VARCHAR(128)    COMMENT '节点说明' ,
    node_condition VARCHAR(1024)    COMMENT '进入条件' ,
    users VARCHAR(128)    COMMENT '审批候选人' ,
    handler_bean_id VARCHAR(50)    COMMENT '业务回调实现类beanId' ,
    notify_bean_id VARCHAR(50)    COMMENT '通知实现类beanId' ,
    create_time DATETIME    COMMENT '创建时间' ,
    update_time DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (id)
) COMMENT = '审批流程配置表';

-- }}

