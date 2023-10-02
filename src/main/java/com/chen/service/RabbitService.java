package com.chen.service;

import com.chen.dto.CourseStudentDTO;
import com.chen.entity.CourseDO;
import com.chen.mapper.CourseMapper;
import com.chen.rabbitmq.DirectRabbitConfig;
import com.chen.util.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RabbitService implements InitializingBean, DisposableBean {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private RedisCache redisCache;

    private final List<String> courseKeys = new ArrayList<>();

    public void sendDemoMessage(CourseStudentDTO message) {
        Assert.notNull(message, "message can not be null!");
        rabbitTemplate.convertAndSend(DirectRabbitConfig.NORMAL_EXCHANGE, DirectRabbitConfig.SAVE_COURSE_ROUTING_KEY, message);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 从mysql里查出所有课程并放入redis中
        List<CourseDO> courses = courseMapper.selectList(null);
        log.info("往redis中添加{}门课", courses.size());
        log.info(String.valueOf(courses.get(0)));
        courses.forEach(each -> {
            String key = String.format("course_count:%d", each.getId());
            courseKeys.add(key);
            redisCache.setCacheObject(key, each.getStock());
        });
        log.info("添加完成!");
    }

    @Override
    public void destroy() throws Exception {
        courseKeys.forEach(each -> redisCache.deleteObject(each));
    }
}
