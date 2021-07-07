package com.aspire.workflow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aspire.workflow.dao.ApprovalLogMapper;
import com.aspire.workflow.domain.ApprovalLog;
import com.aspire.workflow.service.IApprovalLogService;

/**
 * Created by CodeGenerator on 2021/06/09.
 */
@Service
@Transactional
public class ApprovalLogServiceImpl implements IApprovalLogService {
    @Resource
    private ApprovalLogMapper approvalLogMapper;

    @Override
    public void save(ApprovalLog approvalLog) {
        approvalLogMapper.insert(approvalLog);

    }

    @Override
    public List<ApprovalLog> listByBusinessId(String businessId) {
        return approvalLogMapper.listByBusinessId(businessId);
    }

    @Override
    public ApprovalLog getById(String approvalLogId) {
        return approvalLogMapper.getById(approvalLogId);
    }
    
}
