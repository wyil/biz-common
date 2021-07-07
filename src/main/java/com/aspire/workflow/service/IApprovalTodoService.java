package com.aspire.workflow.service;

import com.aspire.workflow.domain.ApprovalTodo;

/**
 * Created by CodeGenerator on 2021/06/09.
 */
public interface IApprovalTodoService {

    /**
     * @Title: done
     * @param todo
     */
    void done(ApprovalTodo todo);

    /**
     * @Title: insert
     * @param approvalTodo
     */
    void insert(ApprovalTodo approvalTodo);

    /**
     * @Title: cancelOtherTodo
     * @param todo
     */
    void cancelOtherTodo(ApprovalTodo todo);

    /**
     * @Title: checkExsitTodo
     * @Description: 检查是否有待办
     * @param todo
     * @return
     */
    boolean checkExsitTodo(ApprovalTodo todo);

}
