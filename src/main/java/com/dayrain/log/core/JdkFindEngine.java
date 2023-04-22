package com.dayrain.log.core;

import com.dayrain.log.entity.Page;
import com.dayrain.log.entity.PageGroup;

import java.nio.charset.StandardCharsets;

/**
 * 利用jdk内置api查找
 * @author peng
 * @version 3.0.0
 * @date 2023/4/5
 */
public class JdkFindEngine implements FindEngine{
    @Override
    public PageGroup find(PageControl pageControl, long startIndex, long endIndex, String keyword) {

        long totalPage = pageControl.getTotalPage();
        PageGroup pageGroup = new PageGroup();
        long start = System.currentTimeMillis();
        for (long i = Math.max(startIndex, 0); i <= Math.min(totalPage - 1, endIndex); i++) {
            Page page = pageControl.getPage(i);
            String content = new String(page.bytes, StandardCharsets.UTF_8);
            if(content.contains(keyword)) {
                //获取缩写
                int shortStart = content.indexOf(keyword);
                int shortEnd = shortStart + keyword.length();
                String abbr = content.substring(Math.max(0, shortStart - 64), Math.min(content.length(), shortEnd + 64));
                pageGroup.getIndexes().add(new PageAbbr(i + 1, abbr));
            }
        }
        long end = System.currentTimeMillis();
        pageGroup.setCost(end - start);
        return pageGroup;
    }
}
