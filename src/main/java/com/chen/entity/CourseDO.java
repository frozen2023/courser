package com.chen.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("course")
public class CourseDO {
    @TableField("id")
    private Long id;
    @TableField("name")
    private String name;
    @TableField("stock")
    private Long stock;
}
