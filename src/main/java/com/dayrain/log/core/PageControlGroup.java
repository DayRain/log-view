package com.dayrain.log.core;

import com.dayrain.log.entity.SystemConfig;

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
public class PageControlGroup {

    private static Map<LinkCredential, PageControl> pageControls = new HashMap<>();

    private static SystemConfig systemConfig;

    public static synchronized PageControl getControl(LinkCredential linkCredential) throws FileNotFoundException {
        PageControl pageControl = pageControls.getOrDefault(linkCredential, null);
        if (pageControl == null) {
            pageControl = new PageControl(linkCredential.getFileUrl(), PageConstants.PAGE_SIZE);
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
