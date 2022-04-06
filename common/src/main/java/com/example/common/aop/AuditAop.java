package com.example.common.aop;

import com.example.common.annotation.AuditLog;
import com.example.common.base.BaseQuery;
import com.example.common.dto.AuditDTO;
import com.example.common.service.GetString;
import com.example.common.service.impl.AuditServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.*;
import java.util.Objects;


/**
 * @author 代志豪
 * 2022/3/17 11:20
 */
@Aspect
@Component
@Slf4j
@ConditionalOnProperty(prefix = "aop", havingValue = "true", value = "config")
public class AuditAop {

    @Autowired
    AuditServiceImpl auditService;

    @Autowired
    GetString getString;

    @Value("${spring.application.name}")
    String value;

    @Pointcut(value = "@annotation(auditLog)")
    public void pointCut(AuditLog auditLog) {
    }


    @AfterReturning(value = "pointCut(auditLog)")
    public void afterReturning(JoinPoint pjp, AuditLog auditLog) throws Throwable {
        check();
        setAudit(pjp, "1");
    }



    @AfterThrowing("pointCut(auditLog)")
    public void throwing(JoinPoint pjp, AuditLog auditLog) {
        setAudit(pjp, "2");
    }


    private void setAudit(JoinPoint pjp, String result) {
        Object[] args = pjp.getArgs();
        BaseQuery baseQuery = (BaseQuery) args[0];
        String desc = baseQuery.getString();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        AuditDTO auditDTO = getString.getString(request);
        auditDTO.setContent(desc);
        auditDTO.setResult(result);
        auditDTO.setModule(value);
        auditService.record(auditDTO);
    }

    private boolean check(){

        return true;
    }
}
