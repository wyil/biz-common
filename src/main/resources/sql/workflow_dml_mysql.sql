-- {{  2021/6/21
INSERT INTO qm_approval_config
(id, flow_key, approval_node, node_desc, node_condition, user_bean_id, handler_bean_id, notify_bean_id, create_time, update_time)
VALUES('101', 'ExpertRequirement', 'start', '开始节点', '[{"_comment":"来着复审拒绝","conditions":[{"dataFrom":"$.approvalFo.approvalNode","dataValue":"REAPPROVAL","validate":"string"},{"dataFrom":"$.approvalFo.action","dataValue":"refuse","validate":"string"}}]', 'ExpertRequirement_CreateUser', 'ExpertRequirement_start', 'ExpertRequirement_RepprovalRefuseNotify', '2021-06-09 00:00:00', NULL);
INSERT INTO qm_approval_config
(id, flow_key, approval_node, node_desc, node_condition, user_bean_id, handler_bean_id, notify_bean_id, create_time, update_time)
VALUES('102', 'ExpertRequirement', 'FRIST_APPROVAL', '初审', '[{"_comment":"来自启动","conditions":[{"dataFrom":"$.approvalFo.approvalNode","dataValue":"start","validate":"string"}}]', 'ExpertRequirement_FirstApprovalUser', 'ExpertRequirement_FristApprovalHandler', 'ExpertRequirement_FristApprovalNotify', '2021-06-09 00:00:00', NULL);
INSERT INTO qm_approval_config
(id, flow_key, approval_node, node_desc, node_condition, user_bean_id, handler_bean_id, notify_bean_id, create_time, update_time)
VALUES('103', 'ExpertRequirement', 'REAPPROVAL', '复审', '{"_comment":"来着初审","conditions":[{"dataFrom":"$.approvalFo.approvalNode","dataValue":"FRIST_APPROVAL","validate":"string"}}]', 'ExpertRequirement_ReapprovalUser', 'ExpertRequirement_ReapprovalHandler', 'ExpertRequirement_ReapprovalNotify', NULL, NULL);
INSERT INTO qm_approval_config
(id, flow_key, approval_node, node_desc, node_condition, user_bean_id, handler_bean_id, notify_bean_id, create_time, update_time)
VALUES('104', 'ExpertRequirement', 'ACCEPT', '需求受理', '[{"_comment":"来着复审","conditions":[{"dataFrom":"$.approvalFo.approvalNode","dataValue":"REAPPROVAL","validate":"string"},{"dataFrom":"$.approvalFo.action","dataValue":"agree","validate":"string"}}]', 'ExpertRequirement_AcceptUser', 'ExpertRequirement_AcceptHandler', 'ExpertRequirement_AcceptNotify', NULL, NULL);
INSERT INTO qm_approval_config
(id, flow_key, approval_node, node_desc, node_condition, user_bean_id, handler_bean_id, notify_bean_id, create_time, update_time)
VALUES('105', 'ExpertRequirement', 'ASSESSMENT', '需求评估', '[{"_comment":"来着受理","conditions":[{"dataFrom":"$.approvalFo.approvalNode","dataValue":"ACCEPT","validate":"string"}}]', 'ExpertRequirement_AssessmentUser', 'ExpertRequirement_AssessmentHandler', NULL, NULL, NULL);
INSERT INTO qm_approval_config
(id, flow_key, approval_node, node_desc, node_condition, user_bean_id, handler_bean_id, notify_bean_id, create_time, update_time)
VALUES('106', 'ExpertRequirement', 'SCHEDULE', '需求决策排期', '[{"_comment":"来着评估节点","conditions":[{"dataFrom":"$.approvalFo.approvalNode","dataValue":"ASSESSMENT","validate":"string"},{"dataFrom":"$.approvalFo.action","dataValue":"agree","validate":"string"}}]', 'ExpertRequirement_ScheduleUser', 'ExpertRequirement_ScheduleHandler', NULL, NULL, NULL);
INSERT INTO qm_approval_config
(id, flow_key, approval_node, node_desc, node_condition, user_bean_id, handler_bean_id, notify_bean_id, create_time, update_time)
VALUES('107', 'ExpertRequirement', 'ONLINE', '需求上线', '[{"_comment":"来着决策排期节点","conditions":[{"dataFrom":"$.approvalFo.approvalNode","dataValue":"SCHEDULE","validate":"string"},{"dataFrom":"$.approvalFo.action","dataValue":"agree","validate":"string"}}]', 'ExpertRequirement_OnlineUser', 'ExpertRequirement_OnlineHandler', NULL, NULL, NULL);
INSERT INTO qm_approval_config
(id, flow_key, approval_node, node_desc, node_condition, user_bean_id, handler_bean_id, notify_bean_id, create_time, update_time)
VALUES('108', 'ExpertRequirement', 'COMMENT', '需求评价', '[{"_comment":"来着需求上线","conditions":[{"dataFrom":"$.approvalFo.approvalNode","dataValue":"ONLINE","validate":"string"}}]', 'ExpertRequirement_CreateUser', 'ExpertRequirement_CommentHandler', NULL, NULL, NULL);
INSERT INTO qm_approval_config
(id, flow_key, approval_node, node_desc, node_condition, user_bean_id, handler_bean_id, notify_bean_id, create_time, update_time)
VALUES('109', 'ExpertRequirement', 'LAST_APPROVAL', '终审', '[{"_comment":"需求评估拒绝","conditions":[{"dataFrom":"$.approvalFo.approvalNode","dataValue":"ASSESSMENT","validate":"string"},{"dataFrom":"$.approvalFo.action","dataValue":"refuse","validate":"string"}]},{"_comment":"需求决策排期拒绝","conditions":[{"dataFrom":"$.approvalFo.approvalNode","dataValue":"SCHEDULE","validate":"string"},{"dataFrom":"$.approvalFo.action","dataValue":"refuse","validate":"string"}]}]', 'ExpertRequirement_LastApprovalUser', 'ExpertRequirement_LastApprovalHandler', NULL, NULL, NULL);
INSERT INTO qm_approval_config
(id, flow_key, approval_node, node_desc, node_condition, user_bean_id, handler_bean_id, notify_bean_id, create_time, update_time)
VALUES('111', 'ExpertRequirement', 'COMPLETE', '已完成', '[{"_comment":"来自终审","conditions":[{"dataFrom":"$.approvalFo.approvalNode","dataValue":"LAST_APPROVAL","validate":"string"}]},{"_comment":"来着需求评价","conditions":[{"dataFrom":"$.approvalFo.approvalNode","dataValue":"COMMENT","validate":"string"}]}]', NULL, NULL, NULL, NULL, NULL);

-- }}
