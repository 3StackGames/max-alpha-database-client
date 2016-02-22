package com.three_stack.maximum_alpha.database_client.factories;

import com.three_stack.maximum_alpha.database_client.pojos.DBAbility;
import com.three_stack.maximum_alpha.database_client.pojos.DBEffect;
import org.bson.Document;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class AbilityFactory {
    public static DBAbility create(Document abilityDocument) {
        if(abilityDocument == null) {
            return null;
        }

        Map<String, Integer> cost = (Map<String, Integer>) abilityDocument.get("cost");
        boolean tap = abilityDocument.getBoolean("tap");
        String text = abilityDocument.getString("text");
        List<Document> effectDocuments = (List<Document>) abilityDocument.get("effects");
        List<DBEffect> effects = effectDocuments.stream()
                .map(EffectFactory::create)
                .collect(Collectors.toList());

        return new DBAbility(cost, tap, text, effects);
    }
}
