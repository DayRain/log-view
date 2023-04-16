package com.dayrain.log.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class R {
    public int code;
    public String message;
    public Object data;

    public R(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public R(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static R success() {
        return new R(200, "success");
    }

    public static R success(Object data) {
        return new R(200, "success", data);
    }

    public static R fail(String message) {
        return new R(-1,message);
    }

    public static R fail(int code, Object data, String message) {
        return new R(code, message, data);
    }
}
