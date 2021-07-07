package com.aspire.workflow.service;

import java.util.List;

import com.aspire.workflow.domain.ApprovalData;

/**
 * Created by CodeGenerator on 2021/06/09.
 */
public interface IApprovalDataService {

    /**
     * @param data
     * @Title: saveOrUpdate
     */
	ApprovalData saveOrUpdate(ApprovalData data);

    /**
     * @Title: list
     * @param approvalFo
     * @return
     */
    List<ApprovalData> listByFlowKeyAndBusinessId(String flowKey, String businessId);

    ApprovalData get(String approvalDataId);

    /**   
     * @Title: delete   
     * @param approvalDataId      
     */
    void delete(String approvalDataId);

}
