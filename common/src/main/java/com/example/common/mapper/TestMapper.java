package com.example.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.dto.TestDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 代志豪
 * 2022/3/24 20:45
 */
@Mapper
public interface TestMapper extends BaseMapper<TestDto> {
//    @Select("select truncate(data_length/1024/1024, 4) as data_cap from information_schema.tables where table_schema='test' and table_name ='audit';")
//    float select();
}
