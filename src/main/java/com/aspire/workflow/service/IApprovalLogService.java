package com.aspire.workflow.service;

import java.util.List;

import com.aspire.workflow.domain.ApprovalLog;

/**
 * Created by CodeGenerator on 2021/06/09.
 */
public interface IApprovalLogService {

    /**
     * @Title: save
     * @param approvalLog
     */
    void save(ApprovalLog approvalLog);

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
