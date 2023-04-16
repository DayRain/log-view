package com.dayrain.log.entity;

import com.dayrain.log.core.ServerException;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;

public class JsonParam extends HashMap<String, Object> {
    public String getString(String msg) {
        if(!containsKey(msg)) {
            throw new ServerException(msg + "not exist...");
        }
        try {
            return String.valueOf(get(msg));
        }catch (Exception e) {
            throw new ServerException(msg + "parse to string error...");
        }
    }

    public long getLong(String msg) {
        if(!containsKey(msg)) {
            throw new ServerException(msg + " not exists...");
        }
        try {
            return Long.parseLong(String.valueOf(get(msg)));
        }catch (Exception e) {
            throw new ServerException(msg + "parse to long error...");
        }
    }
}
