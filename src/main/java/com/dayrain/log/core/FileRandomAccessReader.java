package com.dayrain.log.core;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

@Slf4j
public class FileRandomAccessReader {
    private RandomAccessFile randomAccessFile;

    public FileRandomAccessReader(String path) throws FileNotFoundException {
        randomAccessFile = new RandomAccessFile(path, "r");
    }

    public long getFileSize(){
        try {
            return randomAccessFile.length();
        } catch (IOException e) {
            log.error(String.valueOf(e));
        }

        return -1;
    }

    public byte[] readBytes(long fs, long fe) {
        int len = (int) (fe - fs);

        byte[]bytes = new byte[len];
        try {
            randomAccessFile.seek(fs);
            randomAccessFile.readFully(bytes, 0, len);
        } catch (IOException e) {
            log.error(String.valueOf(e));
        }
        return bytes;
    }
}
