package com.netflex.stand_up.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ratings {

    @JsonProperty("Source")
    String source;
    @JsonProperty("Value")
    String value;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("");
        sb.append(source);
        sb.append("='").append(value).append('\'');
        return sb.toString();
    }
}