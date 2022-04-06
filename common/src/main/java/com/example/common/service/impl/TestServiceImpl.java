package com.example.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.mapper.TestMapper;
import com.example.common.service.TestService;
import com.example.common.dto.TestDto;
import org.springframework.stereotype.Service;

/**
 * @author 代志豪
 * 2022/3/24 20:49
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, TestDto> implements TestService {

    public void record(TestDto dto){
        save(dto);
    }

//    @Override
//    public float getCap(){
//        return getBaseMapper().select();
//    }

}
