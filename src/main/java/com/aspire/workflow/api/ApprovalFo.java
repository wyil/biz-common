/*
 * aspire-tech.com Inc.
 * Copyright (c) 2000-2019 All Rights Reserved.
 */
package com.aspire.workflow.api;


import com.aspire.common.vo.StaffVo;

import lombok.Data;

/**
 * @ClassName: ApprovalFo
 * @author 黄瑞文
 * @date 2021年6月9日
 */
@Data
public class ApprovalFo {
    private String flowKey;
    private String businessId;
    private String approvalNode;
    private String action;
    private String opinion;
    private StaffVo currentUser;
    /**
     * @Fields supporterId : 当前站点id
     */
    private String supporterId;
    private String supporterName;
    private String roleType;
    private String businessData;
}
