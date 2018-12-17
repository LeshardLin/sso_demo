package com.jhkj.sso_demo.util;


import org.apache.commons.lang3.StringUtils;

/**
 * @auther: LinYan
 * @date: 2018/11/30 15:47
 * @description: 层级工具类
 */
public class LevelUtil {
    public static final String SEPARATOR = ".";
    public static final String ROOT = "0";

    /**
     * 用于计算当前层级
     *
     * @param parentLevel 父级的层级
     * @param parentId    父级的id
     * @return 计算后生成的层级
     */
    public static String calculateLevel(String parentLevel, int parentId) {
        //如果父级level为空，代表为首层
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            //如果不为首层，将层级拼接，计算出当前的层级。
            //假设当前为第二层，拼装后当前层级为0(parentLevel).X(SEPARATOR + parentId)
            //如果为第三层，拼装后当前层级为0.X(parentLevel).X(SEPARATOR + parentId)
            return StringUtils.join(parentLevel, SEPARATOR + parentId);
        }
    }
}
