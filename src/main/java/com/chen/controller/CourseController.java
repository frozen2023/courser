package com.chen.controller;

import com.chen.common.CommonResult;
import com.chen.dto.CourseStudentDTO;
import com.chen.service.CourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CourseController {

    @Resource
    private CourseService courseService;

    @PostMapping("/course/seize")
    public CommonResult seizeCourse(@RequestBody CourseStudentDTO requestParam) {
        return courseService.seizeCourse(requestParam);
    }
}
