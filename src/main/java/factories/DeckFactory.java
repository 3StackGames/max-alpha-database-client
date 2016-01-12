package factories;

import org.bson.Document;
import org.bson.types.ObjectId;
import pojos.Deck;

import java.util.List;

public class DeckFactory {

    @SuppressWarnings("unchecked")
    public static Deck create(Document deckDocument) {
        ObjectId id = deckDocument.getObjectId("_id");
        ObjectId ownerId = deckDocument.getObjectId("owner");
        List<ObjectId> mainDeckIds = (List<ObjectId>) deckDocument.get("mainCards");
        List<ObjectId> buildableIds = (List<ObjectId>) deckDocument.get("structures");
        return new Deck(id, ownerId, mainDeckIds, buildableIds);
    }
}
