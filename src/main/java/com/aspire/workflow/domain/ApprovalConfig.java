package com.aspire.workflow.domain;

import java.io.Serializable;
import java.util.Date;

public class ApprovalConfig  implements Serializable {
    /**
     * @Fields id id 主键id
     */
    private String id;

    /**
     * @Fields flowKey 流程key（模块）
     */
    private String flowKey;

    /**
     * @Fields approvalNode 审批节点
     */
    private String approvalNode;

    /**
     * @Fields nodeDesc 节点说明
     */
    private String nodeDesc;

    /**
     * @Fields nodeCondition 进入条件
     */
    private String nodeCondition;

    /**
     * @Fields users 审批候选人查询beanId
     */
    private String userBeanId;

    /**
     * @Fields handlerBeanId 业务回调实现类beanId
     */
    private String handlerBeanId;

    /**
     * @Fields notifyBeanId 通知实现类beanId
     */
    private String notifyBeanId;

    /**
     * @Fields createTime 创建时间
     */
    private Date createTime;

    /**
     * @Fields updateTime 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlowKey() {
        return flowKey;
    }

    public void setFlowKey(String flowKey) {
        this.flowKey = flowKey;
    }

    public String getApprovalNode() {
        return approvalNode;
    }

    public void setApprovalNode(String approvalNode) {
        this.approvalNode = approvalNode;
    }

    public String getNodeDesc() {
        return nodeDesc;
    }

    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc;
    }

    public String getNodeCondition() {
        return nodeCondition;
    }

    public void setNodeCondition(String nodeCondition) {
        this.nodeCondition = nodeCondition;
    }

    public String getUserBeanId() {
        return userBeanId;
    }

    public void setUserBeanId(String userBeanId) {
        this.userBeanId = userBeanId;
    }

    public String getHandlerBeanId() {
        return handlerBeanId;
    }

    public void setHandlerBeanId(String handlerBeanId) {
        this.handlerBeanId = handlerBeanId;
    }

    public String getNotifyBeanId() {
        return notifyBeanId;
    }

    public void setNotifyBeanId(String notifyBeanId) {
        this.notifyBeanId = notifyBeanId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ApprovalConfig [id=");
        builder.append(id);
        builder.append(", flowKey=");
        builder.append(flowKey);
        builder.append(", approvalNode=");
        builder.append(approvalNode);
        builder.append(", nodeDesc=");
        builder.append(nodeDesc);
        builder.append(", nodeCondition=");
        builder.append(nodeCondition);
        builder.append(", userBeanId=");
        builder.append(userBeanId);
        builder.append(", handlerBeanId=");
        builder.append(handlerBeanId);
        builder.append(", notifyBeanId=");
        builder.append(notifyBeanId);
        builder.append(", createTime=");
        builder.append(createTime);
        builder.append(", updateTime=");
        builder.append(updateTime);
        builder.append("]");
        return builder.toString();
    }
}