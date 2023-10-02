package com.chen.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {

    public static final String NORMAL_EXCHANGE = "normal.exchange";

    public static final String SAVE_COURSE_QUEUE = "demo.queue";

    public static final String SAVE_COURSE_ROUTING_KEY = "demo.routing";

    // 声明普通队列
    @Bean
    public Queue saveCourseQueue() {
        /**
         * 1、name:    队列名称
         * 2、durable: 是否持久化
         * 3、exclusive: 是否独享、排外的。如果设置为true，定义为排他队列。则只有创建者可以使用此队列。也就是private私有的。
         * 4、autoDelete: 是否自动删除。也就是临时队列。当最后一个消费者断开连接后，会自动删除。
         * */
        return new Queue(SAVE_COURSE_QUEUE, true, false, false);
    }

    //Direct交换机
    @Bean
    public DirectExchange normalExchange() {
        return new DirectExchange(NORMAL_EXCHANGE, true, false);
    }

    // 绑定 demo队列和 普通交换机
    @Bean
    public Binding bindDirect(@Qualifier("normalExchange") DirectExchange exchange,
                              @Qualifier("saveCourseQueue") Queue queue) {
        return BindingBuilder
                //绑定队列
                .bind(queue)
                //到交换机
                .to(exchange)
                //并设置匹配键
                .with(SAVE_COURSE_ROUTING_KEY);
    }
}
