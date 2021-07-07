package com.aspire.workflow.api;

import java.io.Serializable;

/**
 * @ClassName: ApprovalReturnFo
 * @Description: 业务处理完成后的返回对象
 * @author 黄瑞文
 * @date 2021年6月10日
 */
public class ApprovalReturnFo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String businessId;
    private String businessName;

    /**
     * @Fields opinion 审批意见
     */
    private String opinion;

    /**
     * @Fields approvalResult 审批动作+结果
     */
    private String approvalResult;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(String approvalResult) {
        this.approvalResult = approvalResult;
    }

}