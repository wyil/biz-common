package com.aspire.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aspire.common.service.AccountService;
import com.aspire.common.vo.StaffVo;
import com.aspire.webbas.common.lang.entity.ISessionUser;
import com.aspire.webbas.common.sessionuser.api.SessionUserApi;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private SessionUserApi sessionUserApi;

    @Value("${currentUser.from:}")
    private String userFrom;

    /**
     * 取出当前登陆用户
     */
    public StaffVo getUpcUser() {

        // 1、通过session获取用户信息
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//                .getRequest();
        // 2、通过webbas的工具类获取用户信息
//        LoginHelper.getLoginStaff(getSession());
        // 3、或者spring.security
        StaffVo stff = new StaffVo();
        if ("local".equalsIgnoreCase(userFrom)) {
            stff.setStaffId("0001");
            stff.setRealName("用户名(未实现)");
            stff.setLoginName("TestUser001");
            stff.setCompany("卓望数码");
            stff.setPhone("18700001111");
            stff.setEmail("test@126.com");
            stff.setDeptName("开发二部");
            stff.setOrgId("O_001");
        } else {
            ISessionUser sessionUser = sessionUserApi.getSessionUser();
            stff.setStaffId(sessionUser.getId());
            stff.setRealName(sessionUser.getName());
            stff.setLoginName(sessionUser.getUsername());
            stff.setCompany(sessionUser.getOrganizationName());
            stff.setPhone(null);
            stff.setEmail(null);
            stff.setDeptName(sessionUser.getOrganizationName());
            stff.setOrgId(sessionUser.getOrganizationId());
        }
        return stff;
    }

}
