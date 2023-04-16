package com.dayrain.log;

import com.dayrain.log.core.LinkCredential;
import com.dayrain.log.core.PageControl;
import com.dayrain.log.core.PageControlGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@Slf4j
@SpringBootApplication
public class LogViewStarter {

    public static void main(String[] args) {
        SpringApplication.run(LogViewStarter.class, args);
        new Thread(()->{
            Map<LinkCredential, PageControl> map = PageControlGroup.getControlMap();
            for (LinkCredential key : map.keySet()) {
                PageControl pageControl = map.get(key);
                if(!pageControl.isValid()) {
                    map.remove(key);
                }
            }
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

//        new Thread(()->{
//            while (true) {
//                log.info("实时数据test : " + new Date());
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }
}
