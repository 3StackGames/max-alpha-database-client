package com.three_stack.maximum_alpha.database_client.pojos;

import org.bson.Document;

public class DBTag {
    protected String name;
    protected Integer value;

    public DBTag(Document document) {
        this.name = document.getString("name");
        if(document.containsKey("value")) {
            this.value = document.getInteger("value");
        } else {
            value = null;
        }
    }

    public DBTag(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public DBTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
