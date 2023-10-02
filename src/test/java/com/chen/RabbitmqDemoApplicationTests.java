package com.chen;


import com.chen.util.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RabbitmqDemoApplicationTests {

    @Resource
    private RedisCache redisCache;

    @Test
    void contextLoads() {
        Long decrement = redisCache.decrement("course_count:" + 200309, 1);
        System.out.println(decrement);
    }

}
