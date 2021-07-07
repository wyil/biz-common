package com.aspire.workflow.dao;

import java.util.List;

import com.aspire.workflow.domain.ApprovalData;

public interface ApprovalDataMapper {

    /**
     * @Title: get
     * @param data
     * @return
     */
    ApprovalData get(ApprovalData data);

    /**
     * @Title: update
     * @param old
     */
    void update(ApprovalData old);

    /**
     * @Title: insert
     * @param data
     */
    void insert(ApprovalData data);

    /**
     * @Title: insert
     * @param data
     */
    void delete(String id);

    /**   
     * @Title: list   
     * @param approvalFo
     * @return      
     */
    List<ApprovalData> listByFlowKeyAndBusinessId(String flowKey, String businessId);

    /**   
     * @Title: getById   
     * @param id
     * @return      
     */
    ApprovalData getById(String id);
}