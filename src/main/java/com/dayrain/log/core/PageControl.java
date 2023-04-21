package com.dayrain.log.core;

import com.dayrain.log.entity.Page;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class PageControl {
    private String fileUrl;

    private long fileSize;

    private long pageSize;

    private LocalDate lastActiveTime;

    private long totalPage;

    private FileRandomAccessReader reader;

    public PageControl(String fileUrl, long pageSize) throws FileNotFoundException {
        this.fileUrl = fileUrl;
        this.pageSize = pageSize;
        this.reader = new FileRandomAccessReader(fileUrl);
        refresh();
    }

    public synchronized Page getPage(long index) {
        long startIndex = pageSize * index;
        if (index >= totalPage || startIndex > fileSize) {
            return null;
        }

        long endIndex = Math.min(fileSize, startIndex + pageSize);

        updateTime();
        return new Page(startIndex, endIndex, reader.readBytes(startIndex, endIndex));
    }

    public synchronized List<Page> loadPage(long fh, long ft) {

        return new ArrayList<>();
    }

    public synchronized String getRealtimeContent() {
        long realtimeSize = reader.getFileSize();
        if(realtimeSize <= fileSize) {
            return null;
        }

        byte[] bytes = reader.readBytes(fileSize, realtimeSize);
        fileSize = realtimeSize;
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private void updateTime() {
        this.lastActiveTime = LocalDate.now();
    }

    public synchronized void refresh() {
        fileSize = reader.getFileSize();
        totalPage = fileSize % pageSize != 0 ? fileSize / pageSize + 1 : fileSize / pageSize;
        updateTime();
    }

    public synchronized boolean isValid() {
        Period period = Period.between(lastActiveTime, LocalDate.now());
        int days = period.getDays();
        return days <= 1;
    }
}
