package com.aspire.workflow.service;

import java.util.List;

import com.aspire.workflow.domain.ApprovalConfig;

/**
 * Created by CodeGenerator on 2021/06/09.
 */
public interface IApprovalConfigService  {

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
