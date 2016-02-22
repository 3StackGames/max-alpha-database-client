package com.three_stack.maximum_alpha.database_client.pojos;

public class DBStep {
    protected String type;
    protected String name;
    protected String value;

    public DBStep(String type, String name, String value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
