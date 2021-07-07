/*
 * aspire-tech.com Inc.
 * Copyright (c) 2000-2019 All Rights Reserved.
 */
package com.aspire.workflow.api;

import java.util.List;

import com.aspire.workflow.domain.ApprovalConfig;

/**
 * @ClassName: workflowApi
 * @Description: 工作流服务api
 * @author 黄瑞文
 * @date 2021年6月9日
 */
public interface WorkflowApi {

    /**
     * @Title: approval
     * @Description: 流程审批
     * @param approvalFo
     */
    public void approval(ApprovalFo approvalFo);

    /**
     * @Title: nextTodoAndNotify
     * @Description: 下一个节点的待办及审批通知（流程下一个节点是跨站点审批时，无法在当前站点找到待审批用户及发送审批通知）
     * @param approvalFo
     * @param nextApprovalConfig
     */
    public void nextTodoAndNotify(ApprovalFo approvalFo, ApprovalConfig nextApprovalConfig);

    public List<ApprovalDataVo> getApprovalData(String flowKey, String businessId);

    /**   
     * @Title: getApprovalLog   
     * @param flowKey
     * @param businessId      
     */
    public List<ApprovalLogVo> getApprovalLog(String flowKey, String businessId);

    /**   
     * @Title: getNextApprovalNode   
     * @param approvalFo
     * @param listConfig
     * @return      
     */
    ApprovalConfig getNextApprovalNode(ApprovalFo approvalFo, List<ApprovalConfig> listConfig);
    
    /**   
     * @Title: checkExsitTodo   
     * @Description: 是否待审批
     * @param flowKey 流程key
     * @param businessId 业务id
     * @param flowNode  审批节点
     * @param userId    审批人
     * @return      
     */
    boolean checkExsitTodo(String flowKey, String businessId, String flowNode, String userId);
}
