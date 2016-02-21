package com.three_stack.maximum_alpha.database_client.factories;

import com.three_stack.maximum_alpha.database_client.pojos.DBEffect;
import com.three_stack.maximum_alpha.database_client.pojos.DBResult;
import org.bson.Document;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EffectFactory {

    @SuppressWarnings("unchecked")
    public static DBEffect create(Document effectDocument) {
        if(effectDocument == null) {
            return null;
        }
        List<Map<String, Object>> checks = (List<Map<String, Object>>) effectDocument.get("checks");
        List<DBResult> results = ((List<Document>) effectDocument.get("results")).stream()
                .map(DBResult::new)
                .collect(Collectors.toList());
        return new DBEffect(checks, results);
    }
}
