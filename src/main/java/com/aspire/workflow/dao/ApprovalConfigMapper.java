package com.aspire.workflow.dao;

import java.util.List;

import com.aspire.workflow.domain.ApprovalConfig;

public interface ApprovalConfigMapper {

    /**
     * @Title: findByFlowKey
     * @param flowKey
     * @return
     */
    List<ApprovalConfig> findByFlowKey(String flowKey);

    /**
     * @Title: findByFlowKeyAndNode
     * @param flowKey
     * @param approvalNode
     * @return
     */
    ApprovalConfig findByFlowKeyAndNode(String flowKey, String approvalNode);

}