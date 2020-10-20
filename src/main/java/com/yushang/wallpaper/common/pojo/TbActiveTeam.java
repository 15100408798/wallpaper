package com.yushang.wallpaper.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity(name = "tb_active_team")
@Data
public class TbActiveTeam implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "bigint(32) comment '活动跟团人id'")
    private Integer activeTeamId;//id

    private Integer activeUserId;//报名id

    @Column(nullable = false,columnDefinition = "tinyint(1) default 1 comment '1男 2女'")
    private Byte sex;// 1男 2女

    private String userIdcard;//身份证

    private String userName;//姓名

    private String userPhone;//联系电话

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GTM+8")
    @Column(nullable = false,columnDefinition = "datetime(6) comment '创建时间'")
    private Date createTime;	      //创建时间

}
