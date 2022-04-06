package com.example.test1.vo;

import com.example.common.base.BaseQuery;
import lombok.Data;

/**
 * @author 代志豪
 * 2022/3/26 21:29
 */
@Data
public class TestQuery extends BaseQuery {

    private String name;

    private Integer type;

    @Override
    public String getString() {
        String s1 = "用户";
        String s2 = "，查看了test1类型为";
        String s3 = "的数据。";
        return s1 + this.name + s2 + this.type + s3;
    }

}
