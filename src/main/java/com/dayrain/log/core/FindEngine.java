package com.dayrain.log.core;

import com.dayrain.log.entity.PageGroup;
/**
 * 查找引擎
 * @author peng
 * @version 3.0.0
 * @date 2023/4/5
 */
public interface FindEngine {
    /**
     * 根据关键词查找符合条件的结果集
     * @param pageControl 分页管理
     * @param keyword 关键词
     * @return 结果集
     */
    PageGroup find(PageControl pageControl, String keyword);
}
