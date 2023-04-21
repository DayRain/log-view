package com.dayrain.log.core;

import com.dayrain.log.entity.Page;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PageControlTest {

    @org.junit.jupiter.api.Test
    void getPage() throws FileNotFoundException {
        PageControl pageControl =
                new PageControl("E:\\java\\project\\log-view\\logs\\info_2023-04-02.log", 4096);
        Page page = pageControl.getPage(1000);
    }
}