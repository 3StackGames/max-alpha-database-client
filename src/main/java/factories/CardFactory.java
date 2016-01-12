package factories;

import org.bson.Document;
import pojos.Card;
import pojos.Effect;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CardFactory {
    public static Card create(Document cardDocument) {
        long id = cardDocument.getLong("id");
        String name = cardDocument.getString("name");
        String type = cardDocument.getString("type");
        Map<String, Integer> cost = (Map<String, Integer>) cardDocument.get("cost");
        String text = cardDocument.getString("text");
        String flavorText = cardDocument.getString("flavorText");
        Map<String, List<Effect>> effects = (Map<String, List<Effect>>) cardDocument.get("effects");
        Card card = new Card(id, name, type, cost, text, flavorText, effects);

        //get other attributes
        if(cardDocument.containsKey("health")) {
            int health = cardDocument.getInteger("health");
            card.setHealth(health);
        }
        if(cardDocument.containsKey("attack")) {
            int attack = cardDocument.getInteger("attack");
            card.setAttack(attack);
        }
        return card;
    }

    public static List<Card> createAll(List<Document> cardDocuments) {
        return cardDocuments.stream()
                .map(CardFactory::create)
                .collect(Collectors.toList());
    }
}
