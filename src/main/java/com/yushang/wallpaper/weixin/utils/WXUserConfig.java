package com.yushang.wallpaper.weixin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WXUserConfig {

        /**
         * 获取微信授权地址
         */
        public static String getCode(){
            //1、定义一个stringbuilder单线程对象
            StringBuilder str = new StringBuilder();
            //2、拼接字符串
            str.append(WXConfig.codeUrl);
            str.append("appid="+WXConfig.appid);
            str.append("&");
            str.append("redirect_uri="+WXConfig.redirectUri);
            str.append("&");
            str.append("response_type=code");
            str.append("&");
            str.append("scope=snsapi_userinfo");
            str.append("&");
            str.append("state=1#wechat_redirect");
            //3、返回信息
            return str.toString();
        }

    /**
     * 获取发送access_token地址
     */
    public static String getAccessToken(String code){
        //1、定义一个stringbuilder单线程对象
        StringBuilder str = new StringBuilder();
        //2、拼接字符串
        str.append(WXConfig.accessTokenUrl);
        str.append("appid="+WXConfig.appid);
        str.append("&");
        str.append("secret="+WXConfig.secret);
        str.append("&");
        str.append("code="+code);
        str.append("&");
        str.append("grant_type=authorization_code");
        //3、返回信息
        return str.toString();
    }

    /**
     * 获取个人信息地址
     */
    public static String getUserInfoUrl(String accessToken,String openid){
        //1、定义一个stringbuilder单线程对象
        StringBuilder str = new StringBuilder();
        //2、拼接字符串
        str.append(WXConfig.getUserInfoUrl);
        str.append("access_token="+accessToken);
        str.append("&");
        str.append("openid="+openid);
        str.append("&");
        str.append("lang=zh_CN");
        //3、返回信息
        return str.toString();
    }

    /**
     * 打开连接
     */
    public static String getUrlResult(String urlStr, String encoding) throws IOException {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("GET");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));
            // 往对端写完数据对端服务器返回数据 ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }


}
