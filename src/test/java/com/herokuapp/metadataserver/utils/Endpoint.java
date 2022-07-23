package com.herokuapp.metadataserver.utils;

public enum Endpoint {
    METADATA("metadata/%s"),
    METADATA_QUERY("metadata/query"),
    METADATA_PROPERTY_NAME("metadata/%s/properties/name");

    private final String endpoint;

    Endpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String format(String... args) {
        return String.format(this.endpoint, args);
    }
}
