package com.yushang.wallpaper.common.pojo.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 日志类
 */
@Setter
@Getter
@NoArgsConstructor
@Entity(name = "tb_log")
public class TbLog implements Serializable {

    private static final long serialVersionUID = 4655942685704090655L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "bigint(64) comment '日志id'")
    private Long systemLogId;   // 日志id

    @Column(nullable = false, columnDefinition = "smallint(6) comment '操作人id'")
    private Short operateId;        // 操作人id

    @Column(nullable = false, columnDefinition = "tinyint(1) comment '传送方法  1-GET,2-POST'")
    private Byte type;             //  传送方法  1-GET,2-POST

    @Column(nullable = false, columnDefinition = "tinyint(1) comment '请求响应状态：1-成功，2-失败'")
    private Byte isSuccess;        // 请求响应状态：1-成功，2-失败

    @Column(nullable = false, columnDefinition = "varchar(300) comment '传递的参数'")
    private String requestParam;   // 传递的参数

    @Column(nullable = false, columnDefinition = "varchar(100) comment '方法url'")
    private String requestUri;     // url

    @Column(nullable = false, columnDefinition = "varchar(100) comment '方法位置所在'")
    private String methodName;  // 方法位置所在

    @Column(nullable = false, columnDefinition = "varchar(20) comment '操作者的ip'")
    private String managerIp;   // 操作者的ip

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @Column(nullable = false, columnDefinition = "timestamp(6) comment '创建时间'")
    private Date createTime;          //创建时间

    @Column(nullable = false, columnDefinition = "varchar(32) comment '操作名称'")
    private String title; // 操作名称

    @Column(nullable = false, columnDefinition = "tinyint(1) comment '操作类型  1 查询  2 删除 3  开启   4禁用 5 上架 6  下架'")
    private Byte operateType; // 操作类型    1 查询	2 删除	3  开启	  4禁用, 5 上架, 6 下架

    @Column(nullable = false, columnDefinition = "varchar(16) comment '操作表名'")
    private String tabName;   // 操作表名

    @Column(nullable = false, columnDefinition = "varchar(16) comment '日志标识'")
    private String sessionId;   // 日志标识

    private transient String userName;  // 操作者账号
    private transient String userNick;  // 操作者名字


}
