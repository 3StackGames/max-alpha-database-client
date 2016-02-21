package com.three_stack.maximum_alpha.database_client.pojos;

import org.bson.Document;

import java.util.Map;

public class DBResult {
    protected String type;
    /**
     * object in this case is either a number, an ObjectId, or a DBStep
     */
    protected Map<String, Object> value;

    public DBResult(Document resultDocument) {
        this.type = resultDocument.getString("type");
        this.value = (Map<String, Object>) resultDocument.get("value");
    }

    public DBResult(String type, Map<String, Object> value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getValue() {
        return value;
    }

    public void setValue(Map<String, Object> value) {
        this.value = value;
    }
}
