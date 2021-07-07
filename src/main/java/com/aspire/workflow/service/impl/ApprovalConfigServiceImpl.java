package com.aspire.workflow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aspire.workflow.dao.ApprovalConfigMapper;
import com.aspire.workflow.domain.ApprovalConfig;
import com.aspire.workflow.service.IApprovalConfigService;

/**
 * Created by CodeGenerator on 2021/06/09.
 */
@Service
@Transactional
public class ApprovalConfigServiceImpl implements IApprovalConfigService {
    @Resource
    private ApprovalConfigMapper approvalConfigMapper;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.aspire.sims.qm.workflow.service.IApprovalConfigService#findByFlowKey(java
     * .lang.String)
     */
    @Override
    public List<ApprovalConfig> findByFlowKey(String flowKey) {
        return approvalConfigMapper.findByFlowKey(flowKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.sims.qm.workflow.service.IApprovalConfigService#
     * findByFlowKeyAndNode(java.lang.String, java.lang.String)
     */
    @Override
    public ApprovalConfig findByFlowKeyAndNode(String flowKey, String approvalNode) {
        return approvalConfigMapper.findByFlowKeyAndNode(flowKey, approvalNode);
    }

}
