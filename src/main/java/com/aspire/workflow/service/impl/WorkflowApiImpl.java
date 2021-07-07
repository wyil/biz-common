/*
 * aspire-tech.com Inc.
 * Copyright (c) 2000-2019 All Rights Reserved.
 */
package com.aspire.workflow.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aspire.common.vo.StaffVo;
import com.aspire.workflow.api.ApprovalDataVo;
import com.aspire.workflow.api.ApprovalFo;
import com.aspire.workflow.api.ApprovalLogVo;
import com.aspire.workflow.api.ApprovalReturnFo;
import com.aspire.workflow.api.BusinessHandler;
import com.aspire.workflow.api.BusinessNotify;
import com.aspire.workflow.api.BusinessUserFinder;
import com.aspire.workflow.api.WorkflowApi;
import com.aspire.workflow.bo.ApprovalCondition;
import com.aspire.workflow.constant.ApprovalActionConstant;
import com.aspire.workflow.domain.ApprovalConfig;
import com.aspire.workflow.domain.ApprovalData;
import com.aspire.workflow.domain.ApprovalLog;
import com.aspire.workflow.domain.ApprovalTodo;
import com.aspire.workflow.exception.BusinessException;
import com.aspire.workflow.service.IApprovalConfigService;
import com.aspire.workflow.service.IApprovalDataService;
import com.aspire.workflow.service.IApprovalLogService;
import com.aspire.workflow.service.IApprovalTodoService;
import com.aspire.workflow.utils.UuidUtil;

/**
 * @ClassName: WorkflowApiImpl
 * @author 黄瑞文
 * @date 2021年6月9日
 */
@Service("workflowApi")
public class WorkflowApiImpl implements WorkflowApi {
//	private static final Logger LOGGER = Logger.getLogger(WorkflowApiImpl.class);
    @Resource
    private IApprovalConfigService approvalConfigService;
    @Resource
    private IApprovalDataService approvalDataService;
    @Resource
    private IApprovalLogService approvalLogService;
    @Resource
    private IApprovalTodoService approvalTodoService;
    @Resource
    BusinessHandler handler;
    @Resource
    BusinessNotify notify;
    @Resource
    BusinessUserFinder finde;
   
    
    
    @Override
    public void approval(ApprovalFo approvalFo) {
//        LOGGER.info("执行审批，approvalFo=" + approvalFo);
        ApprovalConfig approvalConfig = getCurrentApprovalNode(approvalFo);
        if (StringUtils.isEmpty(approvalConfig.getHandlerBeanId())) {
        	 throw new BusinessException("流程处理实现缺失");
        }
        // 执行业务处理器，业务处理器负责：校验业务数据、更新业务数据
       
        ApprovalReturnFo returnFo = handler.excute(approvalFo);
        if(!StringUtils.isEmpty(returnFo.getBusinessId())) {
            approvalFo.setBusinessId(returnFo.getBusinessId());
        }else {
            returnFo.setBusinessId(approvalFo.getBusinessId());
        }
        // 保存流程日志（处理日志）
        ApprovalLog approvalLog = saveApprovalLog(approvalFo, returnFo);
        // 保存审批节点数据
        ApprovalData approvalData = saveOrUpdateApprovalData(approvalFo);
        
        //发送同步消息，单站点调试阶段先注释掉  guo_jg 
        handler.sendMsg(returnFo.getBusinessId(), approvalLog.getId(), approvalData.getId());

        // 把当前的待办标记为已办,撤回不用执行已办（ 会签功能待实现）
        if (!ApprovalActionConstant.RECALL.equalsIgnoreCase(approvalFo.getAction())) {
            finishCurrentTodo(approvalFo);
        }
        cancelOtherTodo(approvalFo);

        // 查找下个节点
        List<ApprovalConfig> allConfig = approvalConfigService.findByFlowKey(approvalFo.getFlowKey());
        ApprovalConfig nextApprovalConfig = getNextApprovalNode(approvalFo, allConfig);

        //===========================================
        // 获取下个节点的审批人，需要到站点重新获取审批人、生成待办记录、生成审批通知
        List<StaffVo> todoUsers = getTodoUsers(nextApprovalConfig,
                approvalFo);
        // 生成下个审批节点的待办
        saveTodo(todoUsers, approvalFo, nextApprovalConfig);

        // 调用业务的消息通知   会签功能暂时不实现
        if (!StringUtils.isEmpty(nextApprovalConfig.getNotifyBeanId())) {
            notify.excute(approvalFo, todoUsers);
        }

//        LOGGER.info("执行审批完成");
    }

    /**
     * @Title: saveApprovalLog
     * @param approvalFo
     * @param returnFo
     */
    private ApprovalLog saveApprovalLog(ApprovalFo approvalFo, ApprovalReturnFo returnFo) {
        ApprovalLog approvalLog = new ApprovalLog();
        approvalLog.setId(UuidUtil.getShortId());
        approvalLog.setFlowKey(approvalFo.getFlowKey());
        approvalLog.setBusinessId(returnFo.getBusinessId());
        approvalLog.setOpinion(approvalFo.getOpinion());
        approvalLog.setApprovalResult(returnFo.getApprovalResult());
//        approvalLog.setFileGroupId();
        approvalLog.setCreateTime(new Date());
        StaffVo user = approvalFo.getCurrentUser();
        approvalLog.setCreateUserId(user.getStaffId());
        // 姓名（站点-部门）
        StringBuilder userName = new StringBuilder(user.getRealName() + "(" + approvalFo.getSupporterName());
        if (user.getDeptName() != null ) {
            userName.append("-" + user.getDeptName());
        }
        userName.append(")");
        approvalLog.setCreateUserName(userName.toString());
        approvalLogService.save(approvalLog);
        return approvalLog ;
        		
    }

    /**
     * @Title: saveOrUpdateApprovalData
     * @param approvalFo
     */
    private ApprovalData saveOrUpdateApprovalData(ApprovalFo approvalFo) {
        ApprovalData data = new ApprovalData();
        data.setId(UuidUtil.getShortId());
        data.setFlowKey(approvalFo.getFlowKey());
        data.setBusinessId(approvalFo.getBusinessId());
        data.setFlowNode(approvalFo.getApprovalNode());
        data.setApprovalAction(approvalFo.getAction());
        data.setOpinion(approvalFo.getOpinion());
        data.setJsonData(approvalFo.getBusinessData());
        data.setCreateUserId(approvalFo.getCurrentUser().getStaffId());
        data.setCreateUserName(approvalFo.getCurrentUser().getRealName());
        data.setCreateTime(new Date());
        data.setDeleted("0");
        return approvalDataService.saveOrUpdate(data);
    }

    /**
     * @Title: saveTodo
     * @Description: 生成流程待办记录
     * @param todoUsers
     * @param nextApprovalConfig
     * @param currentUser
     */
    private void saveTodo(List<StaffVo> todoUsers, ApprovalFo approvalFo,
            ApprovalConfig nextApprovalConfig) {
        if (todoUsers == null) {
            return;
        }
        for (StaffVo staff : todoUsers) {
            ApprovalTodo approvalTodo = new ApprovalTodo();
            approvalTodo.setId(UuidUtil.getShortId());
            approvalTodo.setFlowKey(approvalFo.getFlowKey());
            approvalTodo.setBusinessId(approvalFo.getBusinessId());
            approvalTodo.setFlowNode(nextApprovalConfig.getApprovalNode());
            approvalTodo.setUserId(staff.getStaffId());
            approvalTodo.setIsDo(0);
            approvalTodo.setCreateTime(new Date());
            if (approvalFo.getCurrentUser() != null) {
                approvalTodo.setCreateUserId(approvalFo.getCurrentUser().getStaffId());
                approvalTodo.setCreateUserName(approvalFo.getCurrentUser().getRealName());
            }
            approvalTodo.setDeleted("0");
            approvalTodoService.insert(approvalTodo);
        }

    }

    /**
     * @Title: getTodoUsers
     * @Description: 获取流程待办用户
     * @param nextApprovalConfig
     * @param approvalFo
     * @return
     */
    private List<StaffVo> getTodoUsers(ApprovalConfig nextApprovalConfig,
            ApprovalFo approvalFo) {
        if (StringUtils.isEmpty(nextApprovalConfig.getUserBeanId())) {
            return new ArrayList<StaffVo>();
        }
        List<StaffVo> list = finder.excute(approvalFo);
        return list;
    }

    /**
     * @Title: finishCurrentTodo
     * @param approvalFo
     */
    private void finishCurrentTodo(ApprovalFo approvalFo) {
        ApprovalTodo todo = new ApprovalTodo();
        todo.setFlowKey(approvalFo.getFlowKey());
        todo.setBusinessId(approvalFo.getBusinessId());
        todo.setFlowNode(approvalFo.getApprovalNode());
        todo.setUserId(approvalFo.getCurrentUser().getStaffId());
        todo.setUpdateTime(new Date());
        todo.setUpdateUserId(approvalFo.getCurrentUser().getStaffId());
        if(!"start".equals(approvalFo.getApprovalNode())) {
            //校验是否已经审批过，或者是否有审批todo
            boolean exsitTodo =approvalTodoService.checkExsitTodo(todo);
            if(exsitTodo) {
                approvalTodoService.done(todo);
            }else {
                throw new BusinessException("已经审批完成或无权审批！");
            }
        }
    }
    
    
    @Override
    public boolean checkExsitTodo(String flowKey, String businessId, String flowNode, String userId) {
        ApprovalTodo todo = new ApprovalTodo();
        todo.setFlowKey(flowKey);
        todo.setBusinessId(businessId);
        todo.setFlowNode(flowNode);
        todo.setUserId(userId);
        return approvalTodoService.checkExsitTodo(todo);
    }

    /**
     * @Title: cancelOtherTodo
     * @param approvalFo
     */
    private void cancelOtherTodo(ApprovalFo approvalFo) {
        ApprovalTodo todo = new ApprovalTodo();
        todo.setFlowKey(approvalFo.getFlowKey());
        todo.setBusinessId(approvalFo.getBusinessId());
        //如果是撤回操作，将所有待办都取消 ——不传入flowNode，userId
        //如果不是撤回，将指定节点flowNode，和不为userId的待办取消
        if(!ApprovalActionConstant.RECALL.equalsIgnoreCase(approvalFo.getAction())) {
            todo.setFlowNode(approvalFo.getApprovalNode());
            todo.setUserId(approvalFo.getCurrentUser().getStaffId());
        }
        todo.setUpdateTime(new Date());
        todo.setUpdateUserId(approvalFo.getCurrentUser().getStaffId());
        approvalTodoService.cancelOtherTodo(todo);
    }


    /**
     * @Title: getNextApprovalNode
     * @Description: 根据流程信息及业务数据找到下个节点
     * @param approvalFo
     * @return
     */
    @Override
    public ApprovalConfig getNextApprovalNode(ApprovalFo approvalFo, List<ApprovalConfig> listConfig) {
        int matchNode = 0;
        ApprovalConfig matchApprovalConfig = null;
        for (ApprovalConfig approvalConfig : listConfig) {
            /**
             * 条件json示例
             * [{"_comment":"需求评估拒绝","conditions":[{"dataFrom":"$.approvalFo.approvalNode","dataValue":"ASSESSMENT","validate":"string"},
             * {"dataFrom":"$.approvalFo.action","dataValue":"refuse","validate":"string"}]},
             * {"_comment":"需求决策排期拒绝","conditions":[{"dataFrom":"$.approvalFo.approvalNode","dataValue":"SCHEDULE","validate":"string"},
             * {"dataFrom":"$.approvalFo.action","dataValue":"refuse","validate":"string"}]}]
             */
            String conditionJson = approvalConfig.getNodeCondition();
            JSONArray jsonArray = (JSONArray) JSON.parse(conditionJson);

            if (jsonArray!=null && jsonArray.size() > 0) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    JSONObject conditionBranch = jsonArray.getJSONObject(i);
                    List<ApprovalCondition> list = JSONObject
                            .parseArray(JSON.toJSONString(conditionBranch.get("conditions")), ApprovalCondition.class);
                    int matchSize =0;
                    // 多个条件必须成立
                    for (ApprovalCondition condition : list) {
                        if(validateCondition(condition, approvalFo)) {
                            matchSize++;
                        }
                    }
                    // conditions都成立，说明找到符合的一个节点
                    if(matchSize==list.size()) {
                        matchNode++;
                        matchApprovalConfig = approvalConfig;
                    }
                }
            }
        }

        if (matchNode == 0) {
            throw new BusinessException("找不到下一个流程节点，请检查流程配置。");
        } else if (matchNode > 1) {
            throw new BusinessException("找到多个流程节点，请检查流程配置或数据是否正确。");
        }
        return matchApprovalConfig;
    }

    /**
     * @Title: validateCondition
     * @Description: 校验条件是否符合
     * @param condition
     * @param approvalFo
     * @return
     */
    public boolean validateCondition(ApprovalCondition condition, ApprovalFo approvalFo) {
        String dataFrom = getConditionData(condition.getDataFrom(), approvalFo);

        String dataValue = getConditionData(condition.getDataValue(), approvalFo);
        if ("string".equals(condition.getValidate())) {
            if (dataFrom.equals(dataValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Title: getConditionData
     * @Description: 获取数据值
     * @param dataExpression
     * @param approvalFo
     * @return
     */
    public String getConditionData(String dataExpression, ApprovalFo approvalFo) {
        String value = null;
        if (dataExpression.startsWith("$")) {
            if ("$.approvalFo.approvalNode".equalsIgnoreCase(dataExpression)) {
                value = approvalFo.getApprovalNode();
            }
            if ("$.approvalFo.action".equalsIgnoreCase(dataExpression)) {
                value = approvalFo.getAction();
            }
        } else {
            // 默认是字符串值
            value = dataExpression;
        }
        return value;
    }

    /**
     * @Title: getCurrentApprovalNode
     * @Description: 根据flowKey、approvalNode 找到当前审批节点的配置
     * @param approvalFo
     */
    private ApprovalConfig getCurrentApprovalNode(ApprovalFo approvalFo) {
        ApprovalConfig approvalConfig = approvalConfigService.findByFlowKeyAndNode(approvalFo.getFlowKey(),
                approvalFo.getApprovalNode());
        if (approvalConfig == null) {
//            LOGGER.info(approvalFo.toString());
            throw new BusinessException("找不到对应的流程配置。");
        }
        return approvalConfig;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.aspire.sims.qm.workflow.api.WorkflowApi#nextTodoAndNotify(com.aspire.sims
     * .qm.workflow.api.ApprovalFo,
     * com.aspire.sims.qm.workflow.domain.ApprovalConfig)
     */
    @Override
    public void nextTodoAndNotify(ApprovalFo approvalFo, ApprovalConfig nextApprovalConfig) {
        // 获取下个节点的审批人，跨站点审批时会找不到人，需要到站点重新获取审批人、生成待办记录、生成审批通知
        List<StaffVo> todoUsers = getTodoUsers(nextApprovalConfig,
                approvalFo);
        // 生成下个审批节点的待办
        saveTodo(todoUsers, approvalFo, nextApprovalConfig);

        // 调用业务的消息通知 会签功能暂时不实现
        if (!StringUtils.isEmpty(nextApprovalConfig.getNotifyBeanId())) {
            notify.excute(approvalFo, todoUsers);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.aspire.sims.qm.workflow.api.WorkflowApi#getApprovalData(com.aspire.sims.
     * qm.workflow.api.ApprovalFo)
     */
    @Override
    public List<ApprovalDataVo> getApprovalData(String flowKey, String businessId) {
        List<ApprovalData> list = approvalDataService.listByFlowKeyAndBusinessId(flowKey, businessId);
        List<ApprovalDataVo> listVo = new ArrayList<ApprovalDataVo>(list.size());
        for (ApprovalData approvalData : list) {
            ApprovalDataVo vo = new ApprovalDataVo();
            BeanUtils.copyProperties(approvalData, vo);
            listVo.add(vo);
        }
        return listVo;
    }

    @Override
    public List<ApprovalLogVo> getApprovalLog(String flowKey, String businessId) {
        List<ApprovalLog> list = approvalLogService.listByBusinessId(businessId);
        List<ApprovalLogVo> listVo = new ArrayList<ApprovalLogVo>(list.size());
        for (ApprovalLog log : list) {
            ApprovalLogVo vo = new ApprovalLogVo();
            BeanUtils.copyProperties(log, vo);
            listVo.add(vo);
        }
        return listVo;
    }

}
