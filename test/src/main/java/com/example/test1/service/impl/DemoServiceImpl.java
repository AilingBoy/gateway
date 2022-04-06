package com.example.test1.service.impl;

import com.example.test1.service.DemoService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 代志豪
 * 2022/3/25 17:02
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public String get(HttpServletRequest request) {
        request.getParameter("");
        request.getRequestURI();
        return "详情";
    }
}
