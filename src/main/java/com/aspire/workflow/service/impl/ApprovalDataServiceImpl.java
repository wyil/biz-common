package com.aspire.workflow.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aspire.workflow.dao.ApprovalDataMapper;
import com.aspire.workflow.domain.ApprovalData;
import com.aspire.workflow.service.IApprovalDataService;

/**
 * Created by CodeGenerator on 2021/06/09.
 */
@Service
@Transactional
public class ApprovalDataServiceImpl implements IApprovalDataService {
    @Resource
    private ApprovalDataMapper approvalDataMapper;

    /*
     * (non-Javadoc)
     * 
     * @see com.aspire.sims.qm.workflow.service.IApprovalDataService#saveOrUpdate()
     */
    @Override
    public ApprovalData saveOrUpdate(ApprovalData data) {
		ApprovalData old = approvalDataMapper.get(data);
		if (old != null) {
			old.setApprovalAction(data.getApprovalAction());
			old.setOpinion(data.getOpinion());
			old.setJsonData(data.getJsonData());
			old.setUpdateUserId(data.getCreateUserId());
			old.setUpdateTime(new Date());
			approvalDataMapper.update(old);
			return old;
		} else {
			approvalDataMapper.insert(data);
			return data;
		}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.aspire.sims.qm.workflow.service.IApprovalDataService#list(com.aspire.sims
     * .qm.workflow.api.ApprovalFo)
     */
    @Override
    public List<ApprovalData> listByFlowKeyAndBusinessId(String flowKey, String businessId) {
        return approvalDataMapper.listByFlowKeyAndBusinessId(flowKey, businessId);
    }

    @Override
    public ApprovalData get(String approvalDataId) {
        return approvalDataMapper.getById(approvalDataId);
    }

    @Override
    public void delete(String approvalDataId) {
        approvalDataMapper.delete(approvalDataId);
    }

}
