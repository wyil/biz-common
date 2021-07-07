package com.aspire.workflow.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aspire.workflow.dao.ApprovalTodoMapper;
import com.aspire.workflow.domain.ApprovalTodo;
import com.aspire.workflow.service.IApprovalTodoService;

/**
 * Created by CodeGenerator on 2021/06/09.
 */
@Service
@Transactional
public class ApprovalTodoServiceImpl implements IApprovalTodoService {
    @Resource
    private ApprovalTodoMapper approvalTodoMapper;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.aspire.sims.qm.workflow.service.IApprovalTodoService#done(com.aspire.sims
     * .qm.workflow.domain.ApprovalTodo)
     */
    @Override
    public void done(ApprovalTodo todo) {
        approvalTodoMapper.done(todo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.aspire.sims.qm.workflow.service.IApprovalTodoService#insert(com.aspire.
     * sims.qm.workflow.domain.ApprovalTodo)
     */
    @Override
    public void insert(ApprovalTodo approvalTodo) {
        approvalTodoMapper.insert(approvalTodo);

    }

    /* (non-Javadoc)
     * @see com.aspire.sims.qm.workflow.service.IApprovalTodoService#cancelOtherTodo(com.aspire.sims.qm.workflow.domain.ApprovalTodo)
     */
    @Override
    public void cancelOtherTodo(ApprovalTodo todo) {
        approvalTodoMapper.deleteOther(todo);
    }
    @Override
    public boolean checkExsitTodo(ApprovalTodo todo) {
        return approvalTodoMapper.checkExsitTodo(todo);
    }

}
