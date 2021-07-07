/*
 * aspire-tech.com Inc.
 * Copyright (c) 2000-2019 All Rights Reserved.
 */
package com.aspire.workflow.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aspire.workflow.dao.ApprovalConfigMapper;
import com.aspire.workflow.dao.QmBaseDao;
import com.aspire.workflow.domain.ApprovalConfig;

/**
 * @ClassName: ApprovalConfigMapperImpl
 * @author 黄瑞文
 * @date 2021年6月9日
 */
@Repository("approvalConfigMapper")
public class ApprovalConfigMapperImpl extends QmBaseDao implements ApprovalConfigMapper {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.aspire.sims.qm.workflow.dao.ApprovalConfigMapper#findByFlowKey(java.lang.
     * String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ApprovalConfig> findByFlowKey(String flowKey) {
        return (List<ApprovalConfig>) getSqlMapClientTemplate().queryForList("ApprovalConfigMapper.findByFlowKey", flowKey);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.aspire.sims.qm.workflow.dao.ApprovalConfigMapper#findByFlowKeyAndNode(
     * java.lang.String, java.lang.String)
     */
    @SuppressWarnings("deprecation")
    @Override
    public ApprovalConfig findByFlowKeyAndNode(String flowKey, String approvalNode) {
        Map<String, Object> parMap = new HashMap<String, Object>();
        parMap.put("flowKey", flowKey);
        parMap.put("approvalNode", approvalNode);
        return (ApprovalConfig) getSqlMapClientTemplate().queryForObject("ApprovalConfigMapper.findByFlowKeyAndNode", parMap);
    }

}
