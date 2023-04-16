package com.dayrain.log.core;

import com.dayrain.log.entity.Page;
import com.dayrain.log.entity.PageGroup;
/**
 * 利用jdk内置api查找
 * @author peng
 * @version 3.0.0
 * @date 2023/4/5
 */
public class JdkFindEngine implements FindEngine{
    @Override
    public PageGroup find(PageControl pageControl, String keyword) {

        long totalPage = pageControl.getTotalPage();
        PageGroup pageGroup = new PageGroup();
        long start = System.currentTimeMillis();
        for (long i = 0; i < totalPage; i++) {
            Page page = pageControl.getPage(i);
            String content = new String(page.bytes);
            if(content.contains(keyword)) {
                //获取缩写
                int startIndex = content.indexOf(keyword);
                int endIndex = startIndex + keyword.length();
                String abbr = content.substring(Math.max(0, startIndex - 64), Math.min(content.length(), endIndex + 64));
                pageGroup.getIndexes().add(new PageAbbr(i + 1, abbr));
            }
        }
        long end = System.currentTimeMillis();
        pageGroup.setCost(end - start);
        return pageGroup;
    }
}
