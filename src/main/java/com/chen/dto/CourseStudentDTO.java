package com.chen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudentDTO implements Serializable {
    private Long studentId;

    private Long courseId;
}
