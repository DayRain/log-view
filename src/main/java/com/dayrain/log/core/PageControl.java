package com.dayrain.log.core;

import com.dayrain.log.entity.Page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PageControl {
    private String fileUrl;

    private long fileSize;

    private long pageSize;

    private int segmentSize;

    private Date lastActiveTime;

    public List<Page> loadPage(long fh, long ft) {
        return new ArrayList<>();
    }

    public Page getRealtimePage() {
        return null;
    }

    public List<Page> fullPage() {
        return null;
    }
}
