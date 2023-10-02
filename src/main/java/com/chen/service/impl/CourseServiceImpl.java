package com.chen.service.impl;

import com.chen.common.CommonResult;
import com.chen.dto.CourseStudentDTO;
import com.chen.service.CourseService;
import com.chen.service.RabbitService;
import com.chen.util.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Resource
    private RedisCache redisCache;

    @Resource
    private RabbitService rabbitService;

    @Override
    public CommonResult seizeCourse(CourseStudentDTO cs) {
        Long studentId = cs.getStudentId();
        Long courseId = cs.getCourseId();
        log.info("学生{}正在抢{}课", studentId, courseId);
        Long decrement = redisCache.decrement("course_count:" + courseId, 1);
        // 课被抢光
        if (decrement < 0) {
            log.info("抢课失败，课程{}已经被抢完！", courseId);
            redisCache.increment("course_count:" + courseId, 1);
            return CommonResult.error("课已被抢完！");
        }
        // 异步保存选课信息
        rabbitService.sendDemoMessage(cs);
        return CommonResult.success().message("抢课成功！");
    }
}
