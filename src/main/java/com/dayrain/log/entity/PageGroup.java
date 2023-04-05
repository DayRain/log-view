package com.dayrain.log.entity;

import lombok.Data;

import java.util.List;
/**
 * 查询结果集
 * @author peng
 * @version 3.0.0
 * @date 2023/4/5
 */
@Data
public class PageGroup {
    /**
     * 花费时间
     */
    private long cost;
    /**
     * 结果集
     */
    private List<Page> pages;
}
