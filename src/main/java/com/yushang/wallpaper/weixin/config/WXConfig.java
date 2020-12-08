package com.yushang.wallpaper.weixin.config;

public final class WXConfig {

    /**
     * =================================微信授权============================
     */
    /** 授权地址 */
    public final static String codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?";
    /** 微信公众号appid */
    public final static String appid = "wx77af26778620bf3a";

    public final static String secret = "e24dbce60640c408afa0a165e0131acf";//微信公众号密码
    /** 跳转地址 */
    public final static String redirectUri = "http://www.aaarj.com/bicycle/weixin/user/getAccessToken";//

    public final static String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?";//获取用户信息地址

    public final static String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?";//获取个人信息地址


    /**
     * =================================微信支付============================
     */
    public final static String WX_APPLYID = "wx77af26778620bf3a";  //小程序的 appid

    public final static String WX_SECRET = "e24dbce60640c408afa0a165e0131acf"; //小程序的 app secret

    public final static String WX_MCHID = "1516301761";    //商户号

    public final static String WX_SHOPKEY = "08c070f5b74d72ff13bd2517468c99bb";    //商户号的秘钥

    public final static String WX_aliAccessKeyId = "LTAIwbAR76zTue4p";     //阿里云id

    public final static String WX_aliAccessKeySecret = "LLU0ISc4vMPvmHzukrHDBIGgyQcPbw";   //阿里云秘钥

    public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";  //微信支付回调地址

    public static final String createUnifiedOrder = "https://api.mch.weixin.qq.com/pay/unifiedorder";   //微信支付地址


}
