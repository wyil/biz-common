package com.aspire.workflow.dao;

import java.util.List;

import com.aspire.workflow.domain.ApprovalLog;

public interface ApprovalLogMapper  {

    /**   
     * @Title: insert   
     * @param approvalLog      
     */
    void insert(ApprovalLog approvalLog);

    /**   
     * @Title: listByBusinessId   
     * @param businessId
     * @return      
     */
    List<ApprovalLog> listByBusinessId(String businessId);

    /**   
     * @Title: getById   
     * @param approvalLogId
     * @return      
     */
    ApprovalLog getById(String approvalLogId);
}