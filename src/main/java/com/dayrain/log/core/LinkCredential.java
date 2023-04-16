package com.dayrain.log.core;

import lombok.Data;

@Data
public class LinkCredential {
    private String ip;
    private String fileUrl;

    public LinkCredential(String ip, String fileUrl) {
        this.ip = ip;
        this.fileUrl = fileUrl;
    }
}
