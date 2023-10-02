package com.chen.service;

import com.chen.common.CommonResult;
import com.chen.dto.CourseStudentDTO;

public interface CourseService {
    CommonResult seizeCourse(CourseStudentDTO cs);
}
