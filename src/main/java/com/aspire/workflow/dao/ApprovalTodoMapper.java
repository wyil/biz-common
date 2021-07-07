package com.aspire.workflow.dao;

import com.aspire.workflow.domain.ApprovalTodo;

public interface ApprovalTodoMapper {

    /**   
     * @Title: done   
     * @param todo      
     */
    void done(ApprovalTodo todo);

    /**   
     * @Title: deleteOther   
     * @Description: 删除其他待办 不等于userId的  
     * @param todo      
     */
    void deleteOther(ApprovalTodo todo);

    /**   
     * @Title: insert   
     * @param approvalTodo      
     */
    void insert(ApprovalTodo approvalTodo);

    /**   
     * @Title: get   
     * @param id
     * @return      
     */
    ApprovalTodo get(String id);
    
    /**   
     * @Title: delete   
     * @param id      
     */
    void delete(String id);

    /**   
     * @Title: checkExsitTodo   
     * @param todo
     * @return      
     */
    boolean checkExsitTodo(ApprovalTodo todo);
}