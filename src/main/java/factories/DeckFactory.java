package factories;

import org.bson.Document;
import pojos.Deck;

import java.util.List;

public class DeckFactory {
    public static Deck create(Document deckDocument) {
        long id = deckDocument.getLong("id");
        long ownerId = deckDocument.getLong("ownerId");
        List<Long> mainDeckIds = (List<Long>) deckDocument.get("mainDeckIds");
        List<Long> buildableIds = (List<Long>) deckDocument.get("buildableIds");
        return new Deck(id, ownerId, mainDeckIds, buildableIds);
    }
}
