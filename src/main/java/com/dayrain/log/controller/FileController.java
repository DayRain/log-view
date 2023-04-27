package com.dayrain.log.controller;

import com.dayrain.log.core.utils.FileUtils;
import com.dayrain.log.entity.JsonParam;
import com.dayrain.log.entity.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Comparator;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/path/valid")
    public R pathValid(@RequestBody JsonParam jsonParam) {
        String filePath = jsonParam.getString("filePath");
        return R.success(FileUtils.isValidPath(filePath));
    }

    @PostMapping("/file/valid")
    public R fileValid(@RequestBody JsonParam jsonParam) {
        String fileUrl = jsonParam.getString("fileUrl");
        return R.success(FileUtils.isValidFile(fileUrl));
    }

    @PostMapping("/file/name/list")
    public R fileNameList(@RequestBody JsonParam jsonParam) {
        String filePath = jsonParam.getString("filePath");
        String[] names = FileUtils.listFileNames(filePath);
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //info排前面
                if(o1.startsWith("info") && o2.startsWith("sys")) {
                    return -1;
                }

                if(o1.startsWith("sys") && o2.startsWith("info")) {
                    return 1;
                }
                return o2.compareTo(o1);
            }
        });
        return R.success(names);
    }
}
