package com.example.common.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@TableName("audit")
public class AuditDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 事件类型
     */
    private String type;

    /**
     * 功能模块
     */
    private String module;

    /**
     * 事件详情
     */
    private String content;

    /**
     * 事件级别 1系统级事件 2业务级事件
     */
    private String level;

    /**
     * 事件结果 1成功 2失败
     */
    private String result;

    /**
     * 事件时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户ip
     */
    private String userIp;

}
