package com.example.common.service;

import com.example.common.dto.AuditDTO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author 代志豪
 * 2022/3/25 17:41
 */
@Service
@ConditionalOnProperty(prefix = "aop", havingValue = "true", value = "config")
public interface GetString {
    /**
     * 获取详情
     */
    default AuditDTO getString(HttpServletRequest request) {
        AuditDTO auditDTO = new AuditDTO();
        String url = request.getHeader("x-forwarded-for");
        String userId = request.getHeader("sso_session_id2");
        auditDTO.setUserIp(url);
        auditDTO.setUserId(userId);
        auditDTO.setUserName(userId.length() < 10 ? "李四" : "张三");
        auditDTO.setDate(LocalDateTime.now());
        return auditDTO;
    }
}
