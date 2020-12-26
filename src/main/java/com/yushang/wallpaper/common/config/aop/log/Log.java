package com.yushang.wallpaper.common.config.aop.log;

import com.yushang.wallpaper.common.utils.StringTools;
import com.yushang.wallpaper.layer.model.enums.LogEnum;

import java.lang.annotation.*;

@Target(value = ElementType.METHOD)     //说明该注解作用的对象范围
@Documented     //@Documented用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化
@Retention(value = RetentionPolicy.RUNTIME)  //定义该注解停留的时间长短
public @interface Log {

    /**
     * 日志标题
     */
    String title() default StringTools.EMPTY;

    /**
     * 操作的类型
     * 1-查询, 2-删除, 3-开启, 4-禁用
     * 5-上架, 6-下架, 7-推荐, 8-不推荐
     * 9-处理, 10-忽略, 11-添加，12-还原,
     * 13-更新
     */
    byte operateType() default 1;

    /**
     * 操作表名
     */
    String tabName() default StringTools.EMPTY;

    /**
     * 枚举值，操作类型日志
     */
    String logEnum() default LogEnum.UNKNOW;

}
