package com.aspire.common;

import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统日志自定义信息持有
 *
 * @author wangYiliang
 * @date 2020/12/14
 */
public class BusinessLogCustInfoHolder {

    private static final ThreadLocal<Map<String, String>> webLogCustInfoMapHolder = new NamedThreadLocal<Map<String, String>>("切面日志自定义infoMapHolder") {
        @Override
        protected Map<String, String> initialValue() {
            return new HashMap<>();
        }
    };

    /**
     * 设置自定义日志参数信息
     * @param custKey
     * @param custInfo
     */
    public static void setCustInfo(String custKey, String custInfo) {
        webLogCustInfoMapHolder.get().put(custKey, custInfo);
    }

    /**
     * 清空自定义日志参数信息
     */
    public static void resetCustInfo() {
        webLogCustInfoMapHolder.set(new HashMap<>());
    }

    public static Map<String, String> getCustInfo() {
        return webLogCustInfoMapHolder.get();
    }
}
