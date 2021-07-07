/*
 * aspire-tech.com Inc.
 * Copyright (c) 2000-2019 All Rights Reserved.
 */
package com.aspire.workflow.api;

import java.util.List;

import com.aspire.common.vo.StaffVo;

/**
 * @ClassName: BusinessUserFinder
 * @Description: 待办用户查找器
 * @author 黄瑞文
 * @date 2021年6月9日
 */
public abstract class BusinessUserFinder {

    public abstract List<StaffVo> excute(ApprovalFo approvalFo);
}
