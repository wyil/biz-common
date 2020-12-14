package com.aspire.common.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * @ClassName:  StaffVO   
 * @Description: 系统用户对接类——用于对接webbas的用户
 * @author huangruiwen
 * @date   2019年7月3日
 */
@Data
public class StaffVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**   
     * @Fields staffId : 用户id  
     */
    private String staffId;

    /**   
     * @Fields loginName : 登录名  
     */
    private String loginName;
    /**   
     * @Fields realName : 用户名  
     */
    private String realName;

    /**   
     * @Fields deptName : 部门名称  
     */
    private String deptName;

    /**   
     * @Fields company : 所属公司  
     */
    private String company;
    
    /**   
     * @Fields phone : 联系电话
     */
    private String phone;
    
    /**   
     * @Fields email : 邮箱
     */
    private String email;
    
    /**   
     * @Fields orgId : 组织ID 
     */  
    private String orgId;
}