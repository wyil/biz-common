package com.aspire.workflow.domain;

import java.io.Serializable;
import java.util.Date;

public class ApprovalLog  implements Serializable {
    /**
     * @Fields id id 主键id
     */
    private String id;

    /**
     * @Fields flowKey 流程（模块）
     */
    private String flowKey;

    /**
     * @Fields businessId 业务id
     */
    private String businessId;

    /**
     * @Fields opinion 审批意见
     */
    private String opinion;

    /**
     * @Fields approvalResult 审批动作+结果
     */
    private String approvalResult;

    /**
     * @Fields fileGroupId 审批附件
     */
    private String fileGroupId;

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

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
    }

    public String getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(String approvalResult) {
        this.approvalResult = approvalResult == null ? null : approvalResult.trim();
    }

    public String getFileGroupId() {
        return fileGroupId;
    }

    public void setFileGroupId(String fileGroupId) {
        this.fileGroupId = fileGroupId == null ? null : fileGroupId.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", flowKey=").append(flowKey);
        sb.append(", businessId=").append(businessId);
        sb.append(", opinion=").append(opinion);
        sb.append(", approvalResult=").append(approvalResult);
        sb.append(", fileGroupId=").append(fileGroupId);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createUserName=").append(createUserName);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}