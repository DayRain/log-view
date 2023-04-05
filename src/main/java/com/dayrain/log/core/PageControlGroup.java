package com.dayrain.log.core;

import com.dayrain.log.entity.SystemConfig;

import java.util.HashMap;
import java.util.Map;
/**
 * 连接管理
 * @author peng
 * @version 3.0.0
 * @date 2023/4/5
 */
public class PageControlGroup {

    private Map<String, PageControl> pageControls = new HashMap<>();

    private SystemConfig systemConfig;

    public String getConnectId(String ip, String fileUrl) {
        return null;
    }

    public PageControl getControl(String connectId) {
        return null;
    }

    public void removeControl(String connectId) {
    }
}
