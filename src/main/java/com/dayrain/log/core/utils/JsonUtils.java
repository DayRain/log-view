package com.dayrain.log.core.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static ObjectNode buildNode() {
        return mapper.createObjectNode();
    }

}
