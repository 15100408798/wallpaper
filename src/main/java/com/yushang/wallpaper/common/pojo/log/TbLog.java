package com.yushang.wallpaper.common.pojo.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志类
 */
@Setter
@Getter
@NoArgsConstructor
public class TbLog implements Serializable {

    private static final long serialVersionUID = -5519696889465588196L;
    /**
     * 日志id
     */
    private Long systemLogId;
    /**
     * 操作人id
     */
    private Short operateId;
    /**
     * 传送方法  1-GET,2-POST
     */
    private Byte type;
    /**
     * 请求响应状态：1-成功，2-失败
     */
    private Byte isSuccess;
    /**
     * 传递的参数
     */
    private String requestParam;
    /**
     * 方法url
     */
    private String requestUri;
    /**
     * 方法位置所在
     */
    private String methodName;
    /**
     * 操作者的ip
     */
    private String managerIp;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date createTime;
    /**
     * 操作名称
     */
    private String title;
    /***
     * 操作类型
     * 1-查询，2-删除, 3-开启，4-禁用,
     * 5-上架, 6-下架
     */
    private Byte operateType;
    /**
     * 操作表名
     */
    private String tabName;
    /**
     * 日志标识
     */
    private String sessionId;
    /**
     * 操作者账号
     */
    private transient String userName;
    /**
     * 操作者名字
     */
    private transient String userNick;


}
