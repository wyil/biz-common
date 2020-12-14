package com.aspire.common.aspect;

/**
 * @ClassName: WebLogTypeEnum
 * @Description: web请求日志类型
 * @author wangYiliang
 * @since 1.0.0.0
 */
public enum WebLogTypeEnum {

	// 不走工作流日志
	REQUIREMENT_QUERY_1("11A9020101", "需求管理需求创建列表", "查询"), 
	REQUIREMENT_QUERY_2("11A9020102", "需求管理需求评价列表", "查询"),
	REQUIREMENT_QUERY_3("11A9020103", "需求管理需求查询列表", "查询"), 
	REQUIREMENT_QUERY_4("11A9020104", "需求管理需求评估列表、需求上线列表、需求受理列表、需求排期列表", "查询"), 
	
	REQUIREMENT_DETAIL("11A9020112", "需求管理详情查询 ", "详情查询"),

	REQUIREMENT_COMMENTED("11A9020311", "需求管理需求评估列表 ", "评价"),
	
	// 工作流日志
	
	REQUIREMENT_IMPORT("11A9020201", "需求导入", "导入"),
	REQUIREMENT_DRAFT("11A9020301", "需求保存", "保存"),
	REQUIREMENT_SUBMIT("11A9020302", "需求新增", "新增"),
	REQUIREMENT_REPET_SUBMIT("11A9020303", "需求重新提交", "重新提交"),
	REQUIREMENT_DELETE("11A9020304", "需求删除", "删除"),
	REQUIREMENT_ESTIMATED("11A9020305", "需求评估", "评估"),
	REQUIREMENT_TURN("11A9020306", "需求转派", "转派"),
	REQUIREMENT_TERMINATION("11A9020307", "需求终止", "终止"),
	REQUIREMENT_ACCEPTED("11A9020308", "需求受理", "受理"),
	REQUIREMENT_SCHEDULED("11A9020309", "需求排期", "排期"),
	REQUIREMENT_ONLINE("11A9020310", "需求上线", "上线");
	
	private String logType;
	private String businessName;
	private String action;

	private WebLogTypeEnum(String logType, String businessName, String action) {
		this.logType = logType;
		this.businessName = businessName;
		this.action = action;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
