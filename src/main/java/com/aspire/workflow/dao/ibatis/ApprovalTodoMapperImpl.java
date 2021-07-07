package com.aspire.workflow.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.aspire.workflow.dao.ApprovalTodoMapper;
import com.aspire.workflow.dao.QmBaseDao;
import com.aspire.workflow.domain.ApprovalTodo;

@Repository("approvalTodoMapper")
@SuppressWarnings("deprecation")
public class ApprovalTodoMapperImpl extends QmBaseDao implements ApprovalTodoMapper {

    @Override
    public void done(ApprovalTodo todo) {
        getSqlMapClientTemplate().update("ApprovalTodoMapper.done", todo);
    }

    @Override
    public void deleteOther(ApprovalTodo todo) {
        getSqlMapClientTemplate().update("ApprovalTodoMapper.deleteOther", todo);

    }

    @Override
    public void insert(ApprovalTodo approvalTodo) {
        getSqlMapClientTemplate().insert("ApprovalTodoMapper.insert", approvalTodo);

    }
    @Override
    public ApprovalTodo get(String id) {
        return (ApprovalTodo) getSqlMapClientTemplate().queryForObject("ApprovalTodoMapper.get", id);
    }

    @Override
    public void delete(String id) {
        getSqlMapClientTemplate().delete("ApprovalTodoMapper.delete", id);
    }

    @Override
    public boolean checkExsitTodo(ApprovalTodo todo) {
        Integer exsitCount= (Integer) getSqlMapClientTemplate().queryForObject("ApprovalTodoMapper.checkExsitTodo", todo);
        if(exsitCount!=null && exsitCount>0) {
            return true;
        }else {
            return false;
        }
    }
}