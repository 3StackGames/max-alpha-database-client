package com.three_stack.maximum_alpha.database_client.pojos;

import org.bson.Document;

public class DBTag {
    protected String type;
    protected Integer value;

    public DBTag(Document document) {
        this.type = document.getString("type");
        if(document.containsKey("value")) {
            this.value = document.getInteger("value");
        } else {
            value = null;
        }
    }

    public DBTag(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public DBTag(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
