package com.yushang.wallpaper.model.enums;

/**
 * 日志枚举类
 */
public class LogEnum {

    public static final String UNKNOW = "UNKNOW";  // 不明操作

    /**
     * ---------------------------------------------- 管理员 MANAGER ----------------------------------------------------------------
     */
    public static final String USER_LOGIN = "USER_LOGIN";   // 管理员登录
    public static final String MANAGER_LIST = "MANAGER_LIST";   // 管理员列表查询
    public static final String MANAGER_INFO = "MANAGER_INFO";   // 管理员信息查询
    public static final String DEL_MANAGER = "DEL_MANAGER";     // 删除管理员
    public static final String OPEN_MANAGER = "OPEN_MANAGER";   // 开启管理员
    public static final String DISABLED_MANAGER = "DISABLED_MANAGER";   // 禁用管理员
    public static final String INSERT_MANAGER = "INSERT_MANAGER";   // 新增管理员
    public static final String UPDATE_MANAGER = "UPDATE_MANAGER";   // 更新管理员

    /**
     * ---------------------------------------------- 用户 USER ----------------------------------------------------------------
     */
    public static final String USER_LIST = "USER_LIST";   // 用户列表查询
    public static final String DEL_USER = "DEL_USER";     // 删除用户
    public static final String OPEN_USER = "OPEN_USER";   // 开启用户
    public static final String DISABLED_USER = "DISABLED_USER";   // 禁用用户

    /**
     * ---------------------------------------------- 角色 ROLE ----------------------------------------------------------------
     */
    public static final String ROLE_LIST = "ROLE_LIST";   // 角色列表查询
    public static final String DEL_ROLE = "DEL_ROLE";     // 删除角色
    public static final String ADD_ROLE = "ADD_ROLE";     // 新增角色


    /**
     * ---------------------------------------------- 日志 LOG ----------------------------------------------------------------
     */
    public static final String LOG_LIST = "LOG_LIST";   // 日志列表查询


    /**
     * ---------------------------------------------- 商品 PRODUCT ----------------------------------------------------------------
     */
    public static final String PRODUCT_LIST = "PRODUCT_LIST";   // 商品列表查询
    public static final String DEL_PRODUCT = "DEL_PRODUCT";   // 删除商品信息
    public static final String TUI_PRODUCT = "TUI_PRODUCT";   // 推荐商品信息
    public static final String NO_TUI_PRODUCT = "NO_TUI_PRODUCT";   // 不推荐商品信息
    public static final String UP_PRODUCT = "UP_PRODUCT";   // 上架商品信息
    public static final String DOWN_PRODUCT = "DOWN_PRODUCT";   // 下架商品信息


    /**
     * ---------------------------------------------- 商品类别 LABEL ----------------------------------------------------------------
     */
    public static final String PRODUCT_LABEL_LIST = "PRODUCT_LABEL_LIST";   // 商品类别列表查询
    public static final String DEL_PRODUCT_LABEL = "DEL_PRODUCT_LABEL";     // 删除商品类别
    public static final String RED_PRODUCT_LABEL = "RED_PRODUCT_LABEL";     // 还原商品类别
    public static final String INSERT_PRODUCT_LABEL = "INSERT_PRODUCT_LABEL";     // 新增商品类别

    /**
     * ---------------------------------------------- 优惠券 COUPON ----------------------------------------------------------------
     */
    public static final String COUPON_LIST = "COUPON_LIST";  // 优惠券列表查询
    public static final String DEL_COUPON = "DEL_COUPON";  // 删除优惠券信息


    /**
     * ---------------------------------------------- 反馈管理 Feedback ----------------------------------------------------------------
     */
    public static final String FEEDBACK_LIST = "FEEDBACK_LIST";  // 反馈管理列表查询
    public static final String DEL_FEEDBACK = "DEL_FEEDBACK";  // 删除反馈管理信息
    public static final String DISABLE_FEEDBACK = "DISABLE_FEEDBACK";  // 忽略反馈管理信息
    public static final String OPEN_FEEDBACK = "OPEN_FEEDBACK";  // 处理反馈管理信息

    /**
     * ---------------------------------------------- 商户管理 Shop ----------------------------------------------------------------
     */
    public static final String SHOP_LIST = "SHOP_LIST";     // 商户管理列表查询
    public static final String DEL_SHOP = "DEL_SHOP";     // 删除商户管理信息
    public static final String OPEN_SHOP = "OPEN_SHOP";     // 上架商户管理信息
    public static final String DIS_SHOP = "DIS_SHOP";     // 下架商户管理信息

    /**
     * ---------------------------------------------- 商品Sku  ----------------------------------------------------------------
     */
    public static final String SKU_LIST = "SKU_LIST";   // 商品Sku列表查询
    public static final String DEL_SKU = "DEL_SKU";   // 删除商品Sku信息
    public static final String RED_SKU = "RED_SKU";   // 还原商品Sku信息
    public static final String UP_SKU = "UP_SKU";   // 上架商品Sku信息
    public static final String DOWN_SKU = "DOWN_SKU";   // 下架商品Sku信息

    /**
     * ---------------------------------------------- 客服管理 Custom  ----------------------------------------------------------------
     */
    public static final String CUSTOM_LIST = "CUSTOM_LIST";   // 客服管理列表查询
    public static final String DEL_CUSTOM = "DEL_CUSTOM";     // 删除客服信息



}
