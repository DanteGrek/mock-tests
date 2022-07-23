package com.herokuapp.metadataserver.utils;

import java.util.HashMap;
import java.util.Map;

public class BodyBuilder {

    Map<String, Object> body;

    private BodyBuilder() {
        body = new HashMap<String, Object>();
    }

    public BodyBuilder with(String property, Object value) {
        body.put(property, value);
        return this;
    }

    public Map<String, Object> build() {
        return body;
    }

    public static BodyBuilder json(){
        return new BodyBuilder();
    }
}
