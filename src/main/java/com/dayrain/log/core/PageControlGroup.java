package com.dayrain.log.core;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * 连接管理
 *
 * @author peng
 * @version 3.0.0
 * @date 2023/4/5
 */
@Slf4j
public class PageControlGroup {

    private static Map<LinkCredential, PageControl> pageControls = new HashMap<>();

    public static synchronized PageControl getControl(LinkCredential linkCredential) throws FileNotFoundException {
        PageControl pageControl = pageControls.getOrDefault(linkCredential, null);
        if (pageControl == null) {
            pageControl = new PageControl(linkCredential.getFileUrl(), PageConstants.PAGE_SIZE);
            log.info("add control, {}, exists: {}", pageControl, pageControls);
            pageControls.put(linkCredential, pageControl);
        }
        return pageControl;
    }

    public static synchronized void removeControl(LinkCredential linkCredential) {
        pageControls.remove(linkCredential);
    }

    public static synchronized Map<LinkCredential, PageControl> getControlMap() {
        return pageControls;
    }
}
