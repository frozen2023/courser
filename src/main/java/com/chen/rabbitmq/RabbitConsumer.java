package com.chen.rabbitmq;

import com.chen.dto.CourseStudentDTO;
import com.chen.entity.CourseStudentDO;
import com.chen.mapper.CourseStudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class RabbitConsumer {

    @Resource
    private CourseStudentMapper csMapper;

    @RabbitListener(queues = DirectRabbitConfig.SAVE_COURSE_QUEUE)
    public void consumeDemo(@Payload CourseStudentDTO message) {
      log.info("接收到保存选课的消息: {},开始保存选课信息", message);
      // 保存选课信息
      csMapper.insert(new CourseStudentDO(message.getStudentId(), message.getCourseId()));
      log.info("保存完毕！");
    }

}
