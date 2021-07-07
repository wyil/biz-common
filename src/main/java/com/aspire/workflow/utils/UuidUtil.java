/*
 * aspire-tech.com Inc.
 * Copyright (c) 2000-2019 All Rights Reserved.
 */
package com.aspire.workflow.utils;

import java.util.UUID;

/**
 * @ClassName: UuidUtil
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author 黄瑞文
 * @date 2021年6月9日
 */
public class UuidUtil {
    /**
     * @Title: getUUID
     * @Description: 返回不带“-”的UUID
     * @return
     */
    public static String getShortId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
