package com.yurongku.constant;

/**
 * 通用常量
 */
public class CommonConstant {

    /**
     * 成功状态码
     */
    public static final Integer SUCCESS_CODE = 200;

    /**
     * 失败状态码
     */
    public static final Integer FAIL_CODE = 500;

    /**
     * 未授权状态码
     */
    public static final Integer UNAUTHORIZED_CODE = 401;

    /**
     * 禁止访问状态码
     */
    public static final Integer FORBIDDEN_CODE = 403;

    /**
     * 成功消息
     */
    public static final String SUCCESS_MESSAGE = "操作成功";

    /**
     * 失败消息
     */
    public static final String FAIL_MESSAGE = "操作失败";

    /**
     * 用户状态 - 启用
     */
    public static final Integer USER_STATUS_ENABLED = 1;

    /**
     * 用户状态 - 禁用
     */
    public static final Integer USER_STATUS_DISABLED = 0;

    /**
     * 权限类型 - 菜单
     */
    public static final String PERMISSION_TYPE_MENU = "MENU";

    /**
     * 权限类型 - 按钮
     */
    public static final String PERMISSION_TYPE_BUTTON = "BUTTON";

    /**
     * 权限类型 - 接口
     */
    public static final String PERMISSION_TYPE_API = "API";

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * 管理员角色编码
     */
    public static final String ADMIN_ROLE_CODE = "ADMIN";

    /**
     * 普通用户角色编码
     */
    public static final String USER_ROLE_CODE = "USER";

    /**
     * 菜单类型 - 菜单
     */
    public static final String MENU_TYPE_MENU = "MENU";

    /**
     * 菜单类型 - 按钮
     */
    public static final String MENU_TYPE_BUTTON = "BUTTON";

    /**
     * 显示状态 - 显示
     */
    public static final Integer VISIBLE_SHOW = 1;

    /**
     * 显示状态 - 隐藏
     */
    public static final Integer VISIBLE_HIDE = 0;

    /**
     * 是否外链 - 是
     */
    public static final Integer IS_FRAME_YES = 1;

    /**
     * 是否外链 - 否
     */
    public static final Integer IS_FRAME_NO = 0;

    /**
     * 是否缓存 - 是
     */
    public static final Integer IS_CACHE_YES = 1;

    /**
     * 是否缓存 - 否
     */
    public static final Integer IS_CACHE_NO = 0;

    /**
     * 是否主部门 - 是
     */
    public static final Integer IS_PRIMARY_YES = 1;

    /**
     * 是否主部门 - 否
     */
    public static final Integer IS_PRIMARY_NO = 0;
}
