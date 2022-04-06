package com.example.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.dto.AuditDTO;
import com.example.common.mapper.AuditMapper;
import com.example.common.service.AuditService;
import org.springframework.stereotype.Service;

/**
 * @author 代志豪
 * 2022/3/24 20:49
 */
@Service
public class AuditServiceImpl extends ServiceImpl<AuditMapper, AuditDTO> implements AuditService {

    public void record(AuditDTO dto){
        save(dto);
    }

}
