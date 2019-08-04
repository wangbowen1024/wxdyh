package com.wxgzh.enums;

/**
 * Constant class
 * 常量枚举
 *
 * @author BowenWang
 * @date 2019/08/03
 */
public enum ConfigInfo {
    /**
     * 微信公众号原始ID
     */
    WXGZH_ID("gh_453696665e91", 0);

    private String value;
    private Integer id;

    ConfigInfo(String value, Integer id) {
        this.value = value;
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public Integer getId() {
        return id;
    }
}
