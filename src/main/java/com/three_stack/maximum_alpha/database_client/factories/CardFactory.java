package com.three_stack.maximum_alpha.database_client.factories;

import com.three_stack.maximum_alpha.database_client.pojos.DBAbility;
import com.three_stack.maximum_alpha.database_client.pojos.DBTag;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.three_stack.maximum_alpha.database_client.pojos.DBCard;
import com.three_stack.maximum_alpha.database_client.pojos.DBEffect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardFactory {
    @SuppressWarnings("unchecked")
    public static DBCard create(Document cardDocument) {
        ObjectId id = cardDocument.getObjectId("_id");
        String name = cardDocument.getString("name");
        String type = cardDocument.getString("type");
        Map<String, Integer> cost = (Map<String, Integer>) cardDocument.get("cost");
        String text = cardDocument.getString("text");
        String flavorText = cardDocument.getString("flavorText");

        DBCard card = new DBCard(id, name, type, cost, text, flavorText);

        //get optional attributes
        if(cardDocument.containsKey("triggerEffects")) {
            Map<String, List<Document>> triggerEffectDocuments = (Map<String, List<Document>>) cardDocument.get("triggerEffects");
            if (triggerEffectDocuments != null) {
                Map<String, List<DBEffect>> triggerEffects = triggerEffectDocuments.entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().stream()
                                        .map(EffectFactory::create).collect(Collectors.toList())
                        ));
                card.setTriggerEffects(triggerEffects);
            }
        }
        if (cardDocument.containsKey("health")) {
            int health = cardDocument.getInteger("health");
            card.setHealth(health);
        }
        if (cardDocument.containsKey("attack")) {
            int attack = cardDocument.getInteger("attack");
            card.setAttack(attack);
        }
        if (cardDocument.containsKey("effects")) {
            List<Document> effectDocuments = (List<Document>) cardDocument.get("effects");
            List<DBEffect> effects = effectDocuments.stream()
                    .map(EffectFactory::create)
                    .collect(Collectors.toList());
            card.setEffects(effects);
        }
        if (cardDocument.containsKey("abilities")) {
            List<Document> abilityDocuments = (List<Document>) cardDocument.get("abilities");
            List<DBAbility> abilities = abilityDocuments.stream()
                    .map(AbilityFactory::create)
                    .collect(Collectors.toList());
            card.setAbilities(abilities);
        }
        if (cardDocument.containsKey("tags")) {
            List<Document> tagDocuments = (List<Document>) cardDocument.get("tags");
            List<DBTag> tags = tagDocuments.stream()
                    .map(DBTag::new)
                    .collect(Collectors.toList());
            card.setTags(tags);
        }
        if (cardDocument.containsKey("classes")) {
            List<String> classes = (List<String>) cardDocument.get("classes");
            card.setClasses(classes);
        }
        return card;
    }
}
