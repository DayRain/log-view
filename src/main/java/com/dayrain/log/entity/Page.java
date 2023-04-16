package com.dayrain.log.entity;
/**
 * 数据页
 * @author peng
 * @version 3.0.0
 * @date 2023/4/5
 */
public class Page {
    public long fh;
    public long ft;
    public byte[] bytes;
    public Page pre;
    public Page next;

    public Page(long fh, long ft, byte[] bytes) {
        this.fh = fh;
        this.ft = ft;
        this.bytes = bytes;
    }
}
