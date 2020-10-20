package com.yushang.wallpaper.common.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbCity implements Serializable{

    private Long cityId;//id
    private String cityName;//城市名字
    private Integer cityType;// 1省份  2 城市  3 县区
    private Integer cityParentId;//父类id
}
