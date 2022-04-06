package com.example.test1.demo;

import com.example.common.BaseResponse.BaseResponse;
import com.example.common.annotation.AuditLog;
import com.example.test1.vo.TestQuery;
import org.springframework.web.bind.annotation.*;

/**
 * @author 代志豪
 * 2022/3/24 20:31
 */
@RestController
@RequestMapping("/test")
public class Test1Controller {

    @AuditLog( )
    @GetMapping("/test")
    public BaseResponse<String> test(@RequestParam(name = "name") String name) throws Exception {
        return BaseResponse.success("成功");
    }

    @AuditLog( )
    @GetMapping("/test1")
    public BaseResponse<String> test1(@RequestParam(name = "name") String name,
                                      @RequestParam(name = "desc") String desc) throws Exception {

        return BaseResponse.success("成功");
    }

    @AuditLog()
    @PostMapping("/test2")
    public BaseResponse<String> test2(@RequestBody TestQuery testQuery) throws Exception {
        if (testQuery.getType() == 9) {
            throw new Exception("错错错！");
        }
        return BaseResponse.success("成功");
    }
}
