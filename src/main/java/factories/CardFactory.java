package factories;

import org.bson.Document;
import org.bson.types.ObjectId;
import pojos.Card;
import pojos.Effect;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardFactory {
    @SuppressWarnings("unchecked")
    public static Card create(Document cardDocument) {
        ObjectId id = cardDocument.getObjectId("_id");
        String name = cardDocument.getString("name");
        String type = cardDocument.getString("type");
        Map<String, Integer> cost = (Map<String, Integer>) cardDocument.get("cost");
        String text = cardDocument.getString("text");
        String flavorText = cardDocument.getString("flavorText");
        Map<String, List<Document>> effectDocuments = (Map<String, List<Document>>) cardDocument.get("effects");
        Map<String, List<Effect>> effects = null;
        if (effectDocuments != null) {
            effects = effectDocuments.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> entry.getValue().stream()
                                    .map(EffectFactory::create).collect(Collectors.toList())
                    ));
        }

        Card card = new Card(id, name, type, cost, text, flavorText, effects);

        //get other attributes
        if (cardDocument.containsKey("health")) {
            int health = cardDocument.getInteger("health");
            card.setHealth(health);
        }
        if (cardDocument.containsKey("attack")) {
            int attack = cardDocument.getInteger("attack");
            card.setAttack(attack);
        }
        return card;
    }
}
