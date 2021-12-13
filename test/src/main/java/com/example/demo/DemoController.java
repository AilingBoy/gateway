package com.example.demo;

import com.example.BaseResponse.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 代志豪
 * 2021/12/13 16:29
 */
@RestController
@RequestMapping("/test")
public class DemoController {

    @GetMapping("/get")
    private BaseResponse<String> get(){
        return BaseResponse.success("123");
    }
}
