package com.example.test1.demo;

import com.example.common.BaseResponse.BaseResponse;
import com.example.common.annotation.AuditLog;
import com.example.test1.vo.AuditQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 代志豪
 * 2021/12/13 16:29
 */
@RestController
@RequestMapping("/test")
public class DemoController {

    private static String sss;


    @GetMapping("/get")
    public BaseResponse<String> get() throws Exception {
        System.out.println(System.currentTimeMillis());
        System.out.println("controller触发");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return BaseResponse.success("123");
    }

    @AuditLog()
    @GetMapping("/test")
    public BaseResponse<String> test(@RequestParam(name = "name") String name) throws Exception {
        return BaseResponse.success("成功");
    }

    @AuditLog
    @GetMapping("/test1")
    public BaseResponse<String> test1(@RequestParam(name = "name") String name,
                                      @RequestParam(name = "desc") String desc) throws Exception {

        return BaseResponse.success("成功");
    }

    @AuditLog()
    @GetMapping("/test2")
    public BaseResponse<String> test2(AuditQuery auditQuery) throws Exception {
        if (auditQuery.getType() == 9) {
            throw new Exception("错错错！");
        }
        return BaseResponse.success("成功");
    }

    @AuditLog(type = 1)
    @GetMapping("/aop")
    public BaseResponse<String> test() throws Exception {
        return BaseResponse.success("123,aop");
    }

    @GetMapping("/get1")
    private String getString() {
        return "123";
    }
}
