package com.three_stack.maximum_alpha.database_client.factories;

import com.three_stack.maximum_alpha.database_client.pojos.DBEffect;
import com.three_stack.maximum_alpha.database_client.pojos.DBResult;
import org.bson.Document;

import java.util.List;

public class EffectFactory {

    @SuppressWarnings("unchecked")
    public static DBEffect create(Document effectDocument) {
        if(effectDocument == null) {
            return null;
        }
        List<String> checks = (List<String>) effectDocument.get("checks");
        List<DBResult> results = (List<DBResult>) effectDocument.get("results");
        return new DBEffect(checks, results);
    }
}
