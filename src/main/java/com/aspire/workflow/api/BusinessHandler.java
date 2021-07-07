/*
 * aspire-tech.com Inc.
 * Copyright (c) 2000-2019 All Rights Reserved.
 */
package com.aspire.workflow.api;

/**
 * @ClassName: BusinesHandler
 * @Description: 业务处理类的抽象父类
 * @author 黄瑞文
 * @date 2021年6月9日
 */
public abstract class BusinessHandler {

    public abstract ApprovalReturnFo excute(ApprovalFo approvalFo);
    
	public abstract void sendMsg(String businessId, String approvalLogId, String approvalDataId);
}
