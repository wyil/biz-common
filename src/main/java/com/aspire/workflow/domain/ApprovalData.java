package com.aspire.workflow.domain;

import java.io.Serializable;
import java.util.Date;

public class ApprovalData  implements Serializable {
    /**
     * @Fields id id 主键id
     */
    private String id;

    /**
     * @Fields flowKey 流程key（模块）
     */
    private String flowKey;

    /**
     * @Fields businessId 业务id
     */
    private String businessId;

    /**
     * @Fields flowNode 审批节点
     */
    private String flowNode;

    /**
     * @Fields action 审批动作 agree;refuse;
     */
    private String approvalAction;

    /**
     * @Fields opinion 审批意见
     */
    private String opinion;

    /**
     * @Fields jsonData 审批数据
     */
    private String jsonData;

    /**
     * @Fields createUserId 创建人
     */
    private String createUserId;

    /**
     * @Fields createUserName 创建人姓名
     */
    private String createUserName;

    /**
     * @Fields createTime 创建时间
     */
    private Date createTime;

    /**
     * @Fields updateUserId 更新人
     */
    private String updateUserId;

    /**
     * @Fields updateTime 更新时间
     */
    private Date updateTime;

    /**
     * @Fields deleted deleted 0未删除；1删除
     */
    private String deleted;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey == null ? null : flowKey.trim();
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public String getFlowNode() {
        return flowNode;
    }

    public void setFlowNode(String flowNode) {
        this.flowNode = flowNode == null ? null : flowNode.trim();
    }

    public String getApprovalAction() {
        return approvalAction;
    }

    public void setApprovalAction(String approvalAction) {
        this.approvalAction = approvalAction;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData == null ? null : jsonData.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", flowKey=").append(flowKey);
        sb.append(", businessId=").append(businessId);
        sb.append(", flowNode=").append(flowNode);
        sb.append(", action=").append(approvalAction);
        sb.append(", opinion=").append(opinion);
        sb.append(", jsonData=").append(jsonData);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createUserName=").append(createUserName);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleted=").append(deleted);
        sb.append("]");
        return sb.toString();
    }
}