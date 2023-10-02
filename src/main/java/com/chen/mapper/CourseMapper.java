package com.chen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.entity.CourseDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper extends BaseMapper<CourseDO> {
}
