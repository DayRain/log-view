package com.dayrain.log.entity;
/**
 * 系统配置
 * @author peng
 * @version 3.0.0
 * @date 2023/4/5
 */
public class SystemConfig {
    /**
     * 页大小
     */
    private long pageSize;
    /**
     * 段大小
     */
    private int segmentSize;
    /**
     * 使用内存
     */
    private long memorySize;
    /**
     * 保活时间
     */
    private long keepaliveTime;
}
