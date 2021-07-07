package com.aspire.workflow.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.aspire.workflow.dao.ApprovalDataMapper;
import com.aspire.workflow.dao.QmBaseDao;
import com.aspire.workflow.domain.ApprovalData;

@Repository("approvalDataMapper")
@SuppressWarnings("deprecation")
public class ApprovalDataMapperImpl extends QmBaseDao implements ApprovalDataMapper {


    @Override
    public ApprovalData get(ApprovalData data) {
        return (ApprovalData) getSqlMapClientTemplate().queryForObject("ApprovalDataMapper.get", data);
    }
    
    @Override
    public ApprovalData getById(String id) {
        return (ApprovalData) getSqlMapClientTemplate().queryForObject("ApprovalDataMapper.getById", id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.aspire.sims.qm.workflow.dao.ApprovalDataMapper#update(com.aspire.sims.qm.
     * workflow.domain.ApprovalData)
     */
    @Override
    public void update(ApprovalData data) {
        getSqlMapClientTemplate().update("ApprovalDataMapper.update", data);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.aspire.sims.qm.workflow.dao.ApprovalDataMapper#insert(com.aspire.sims.qm.
     * workflow.domain.ApprovalData)
     */
    @Override
    public void insert(ApprovalData data) {
        getSqlMapClientTemplate().insert("ApprovalDataMapper.insert", data);
    }

    /* (non-Javadoc)
     * @see com.aspire.sims.qm.workflow.dao.ApprovalDataMapper#delete(java.lang.String)
     */
    @Override
    public void delete(String id) {
        getSqlMapClientTemplate().delete("ApprovalDataMapper.delete", id);
    }

    /* (non-Javadoc)
     * @see com.aspire.sims.qm.workflow.dao.ApprovalDataMapper#list(com.aspire.sims.qm.workflow.api.ApprovalFo)
     */
    @Override
    public List<ApprovalData> listByFlowKeyAndBusinessId(String flowKey, String businessId) {
        Map<String, Object> parMap = new HashMap<String, Object>();
        parMap.put("flowKey", flowKey);
        parMap.put("businessId", businessId);
        return (List<ApprovalData>) getSqlMapClientTemplate().queryForList("ApprovalDataMapper.listByFlowKeyAndBusinessId", parMap);
    }
}