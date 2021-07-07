/*
 * aspire-tech.com Inc.
 * Copyright (c) 2000-2019 All Rights Reserved.
 */
package com.aspire.workflow.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aspire.workflow.dao.ApprovalLogMapper;
import com.aspire.workflow.dao.QmBaseDao;
import com.aspire.workflow.domain.ApprovalLog;

/**
 * @ClassName: ApprovalConfigMapperImpl
 * @author 黄瑞文
 * @date 2021年6月9日
 */
@Repository("approvalLogMapper")
@SuppressWarnings("deprecation")
public class ApprovalLogMapperImpl extends QmBaseDao implements ApprovalLogMapper {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.aspire.sims.qm.workflow.dao.ApprovalLogMapper#insert(com.aspire.sims.qm.
     * workflow.domain.ApprovalLog)
     */
    @Override
    public void insert(ApprovalLog approvalLog) {
        getSqlMapClientTemplate().insert("ApprovalLogMapper.insert", approvalLog);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.aspire.sims.qm.workflow.dao.ApprovalLogMapper#listByBusinessId(java.lang.
     * String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ApprovalLog> listByBusinessId(String businessId) {
        return (List<ApprovalLog>) getSqlMapClientTemplate().queryForList("ApprovalLogMapper.listByBusinessId", businessId);
    }

    /* (non-Javadoc)
     * @see com.aspire.sims.qm.workflow.dao.ApprovalLogMapper#getById(java.lang.String)
     */
    @Override
    public ApprovalLog getById(String approvalLogId) {
        return (ApprovalLog) getSqlMapClientTemplate().queryForObject("ApprovalLogMapper.getById", approvalLogId);
    }

}
