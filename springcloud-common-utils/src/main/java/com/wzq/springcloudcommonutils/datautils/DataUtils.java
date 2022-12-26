package com.wzq.springcloudcommonutils.datautils;

import org.apache.commons.lang.StringUtils;

/**
 * @author wuzhaoqiang
 * @date 2021/9/30 11:08
 */
public class DataUtils {

    public static Boolean isNotValue(String value) {
        return StringUtils.isNotEmpty(value);
    }
}
