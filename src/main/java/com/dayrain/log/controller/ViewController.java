package com.dayrain.log.controller;

import com.dayrain.log.core.JdkFindEngine;
import com.dayrain.log.core.LinkCredential;
import com.dayrain.log.core.constants.ServerCode;
import com.dayrain.log.core.utils.JsonUtils;
import com.dayrain.log.core.utils.NetUtils;
import com.dayrain.log.core.PageControl;
import com.dayrain.log.core.PageControlGroup;
import com.dayrain.log.entity.JsonParam;
import com.dayrain.log.entity.Page;
import com.dayrain.log.entity.PageGroup;
import com.dayrain.log.entity.R;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/view")
public class ViewController {

    @PostMapping("/page/query")
    public R query(HttpServletRequest request, @RequestBody JsonParam jsonParam) throws FileNotFoundException {
        long pageIndex = jsonParam.getLong("pageIndex");
        String fileUrl = jsonParam.getString("fileUrl");
        PageControl control = getControl(NetUtils.getIpAddr(request), fileUrl);
        Page page = control.getPage(pageIndex - 1);
        System.out.println(pageIndex - 1);
        return R.success(new String(page.bytes));
    }

    @PostMapping("/page/info")
    public R info(HttpServletRequest request, @RequestBody JsonParam jsonParam) throws FileNotFoundException {
        String fileUrl = jsonParam.getString("fileUrl");
        PageControl control = getControl(NetUtils.getIpAddr(request), fileUrl);
        ObjectNode result = JsonUtils.buildNode().put("totalPage", control.getTotalPage())
                .put("pageSize", control.getPageSize());
        return R.success(result);
    }

    @PostMapping("/page/search")
    public R search(HttpServletRequest request, @RequestBody JsonParam jsonParam) throws FileNotFoundException {
        String fileUrl = jsonParam.getString("fileUrl");
        String keyword = jsonParam.getString("keyword");
        long startIndex = jsonParam.getLong("startIndex");
        long endIndex = jsonParam.getLong("endIndex");
        PageControl control = getControl(NetUtils.getIpAddr(request), fileUrl);
        JdkFindEngine jdkFindEngine = new JdkFindEngine();
        PageGroup pageGroup = jdkFindEngine.find(control, startIndex, endIndex, keyword);
        if (pageGroup.getIndexes().size() > 5000) {
            return R.fail(ServerCode.OVERFLOW, pageGroup.getIndexes().size(), "结果超过5000条");
        }
        Collections.reverse(pageGroup.getIndexes());
        return R.success(pageGroup);
    }

    @PostMapping("/realtime")
    public R realtime(HttpServletRequest request, @RequestBody JsonParam jsonParam) throws FileNotFoundException {
        String fileUrl = jsonParam.getString("fileUrl");
        log.info("load realtime log");
        PageControl control = getControl(NetUtils.getIpAddr(request), fileUrl);
        String realtimeContent = control.getRealtimeContent();
        return R.success(realtimeContent);
    }

    @PostMapping("/refresh")
    public R refresh(HttpServletRequest request, @RequestBody JsonParam jsonParam) throws FileNotFoundException {
        String fileUrl = jsonParam.getString("fileUrl");
        PageControl control = getControl(NetUtils.getIpAddr(request), fileUrl);
        control.refresh();
        return R.success();
    }

    private PageControl getControl(String ip, String fileUrl) throws FileNotFoundException {
        LinkCredential linkCredential = new LinkCredential(ip, fileUrl);
        return PageControlGroup.getControl(linkCredential);
    }
}
