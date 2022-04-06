package com.example.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.dto.AuditDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 代志豪
 * 2022/3/24 20:45
 */
@Mapper
public interface AuditMapper extends BaseMapper<AuditDTO> {
}
