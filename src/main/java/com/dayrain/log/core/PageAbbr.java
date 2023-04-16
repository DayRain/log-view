package com.dayrain.log.core;

import lombok.Data;

@Data
public class PageAbbr {
    private Long index;
    private String abbr;

    public PageAbbr(Long index, String abbr) {
        this.index = index;
        this.abbr = abbr;
    }
}
