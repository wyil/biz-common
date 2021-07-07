/*
 * aspire-tech.com Inc.
 * Copyright (c) 2000-2019 All Rights Reserved.
 */
package com.aspire.workflow.api;

import java.util.List;

import com.aspire.common.vo.StaffVo;

/**
 * @ClassName: BusinesHandler
 * @author 黄瑞文
 * @date 2021年6月9日
 */
public abstract class BusinessNotify {

    public abstract void excute(ApprovalFo approvalFo, List<StaffVo> todoUsers);
}
