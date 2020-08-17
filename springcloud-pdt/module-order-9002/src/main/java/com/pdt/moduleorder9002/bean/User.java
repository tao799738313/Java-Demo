package com.pdt.moduleorder9002.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("mybatisplus")
public class User {

    // 表明数据库中的主键
    // 这样在插入的时候就不用自己去set一个了
    // 如果不打算自动生成，可以把type改成NONE
    // AUTO : 数据库ID自增，需要给数据库设置自增
    // INPUT : 用户输入ID
    // ID_WORKER : 全局唯一ID，雪花算法
    // UUID : 全局唯一ID
    // NONE : 该类型为未设置主键类型
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @Version //乐观锁Version注解
    private Integer version;

    @TableLogic //逻辑删除
    private Integer deleted;

    // 字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
