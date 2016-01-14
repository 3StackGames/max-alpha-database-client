package com.three_stack.maximum_alpha.database_client.factories;

import org.bson.Document;
import com.three_stack.maximum_alpha.database_client.pojos.DBEffect;

import java.util.List;

public class EffectFactory {

    @SuppressWarnings("unchecked")
    public static DBEffect create(Document effectDocument) {
        if(effectDocument == null) {
            return null;
        }
        List<String> checks = (List<String>) effectDocument.get("checks");
        List<String> results = (List<String>) effectDocument.get("results");
        List<Object> values = (List<Object>) effectDocument.get("values");
        return new DBEffect(checks, results, values);
    }
}
