package com.chen.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("course_student")
public class CourseStudentDO {
    @TableField("student_id")
    private Long studentId;
    @TableField("course_id")
    private Long courseId;
}
