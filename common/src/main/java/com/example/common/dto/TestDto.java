package com.example.common.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 代志豪
 * 2022/3/24 20:36
 */
@Data
@TableName("test")
public class TestDto {

    @TableId
    private Long id;

    private String name;

    private Integer type;

    private String description;

    public TestDto() {
    }

    public TestDto(String name) {
        this.name = name;
    }

    public TestDto(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    public TestDto(String name, Integer type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }
}
