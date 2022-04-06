package com.example.test1.service.impl;

import com.example.common.dto.AuditDTO;
import com.example.common.service.GetString;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 代志豪
 * 2022/3/25 17:02
 */
@Service
public class DemoServiceImpl implements GetString {

    @Override
    public AuditDTO getString(HttpServletRequest request) {
        request.getParameter("");
        request.getRequestURI();
        return null;
    }
}
